package com.smxknife.energy.services.metric.infras.dao;

import com.smxknife.energy.services.metric.core.dao.DatapointDao;
import com.smxknife.energy.services.metric.infras.converter.DatapointConverter;
import com.smxknife.energy.services.metric.infras.entity.DatapointData;
import com.smxknife.energy.services.metric.infras.entity.DatapointDataRepository;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2021/5/17
 */
@Service
public class DatapointMySqlDao implements DatapointDao {

	@Autowired
	private DatapointDataRepository datapointDataRepository;
	@Autowired
	private DatapointConverter datapointConverter;

	@Override
	public Datapoint read(String metric, LocalDateTime dateTime, boolean isOk) {
		return datapointDataRepository.findByMetricAndDateTimeAndQuality(metric, dateTime, isOk ? 0 : 1);
	}

	@Override
	public List<Datapoint> read(String metric, LocalDateTime startTime, LocalDateTime endTime, boolean isOk) {
		return datapointDataRepository.findByMetricAndQualityAndDateTimeBetween(metric, isOk ? 0 : 1, startTime, endTime);
	}

	@Override
	public boolean write(Datapoint datapoint) {
		datapointDataRepository.save(datapointConverter.toData(datapoint));
		return true;
	}

	@Override
	public boolean write(List<Datapoint> datapoints) {
		final List<DatapointData> dataList = datapoints.stream().map(datapointConverter::toData).collect(Collectors.toList());
		datapointDataRepository.saveAll(dataList);
		return true;
	}
}
