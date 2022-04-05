package com.smxknife.energy.services.metric.core.service;

import com.smxknife.energy.services.metric.core.dao.DatapointDao;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import com.smxknife.energy.services.metric.spi.service.DatapointService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
@DubboService(version = "v1", interfaceClass = DatapointService.class)
public class DatapointServiceImpl implements DatapointService {

	@Autowired
	private DatapointDao datapointDao;

	@Override
	public Datapoint read(String metric, LocalDateTime dateTime, boolean isOk) {
		return datapointDao.read(metric, dateTime, isOk);
	}

	@Override
	public List<Datapoint> read(String metric, LocalDateTime startTime, LocalDateTime endTime, boolean isOk) {
		return datapointDao.read(metric, startTime, endTime, isOk);
	}

	@Override
	public boolean write(Datapoint datapoint) {
		return datapointDao.write(datapoint);
	}

	@Override
	public boolean write(List<Datapoint> datapoints) {
		return datapointDao.write(datapoints);
	}
}
