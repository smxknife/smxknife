package com.smxknife.energy.services.metric.infras.dao;

import com.smxknife.energy.services.metric.core.dao.MetricDao;
import com.smxknife.energy.services.metric.infras.converter.MetricConverter;
import com.smxknife.energy.services.metric.infras.entity.MetricMeta;
import com.smxknife.energy.services.metric.infras.entity.MetricMetaRepository;
import com.smxknife.energy.services.metric.spi.domain.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author smxknife
 * 2021/5/17
 */
@Service
public class MetricMySqlDao implements MetricDao {

	@Autowired
	private MetricMetaRepository metricMetaRepository;
	@Autowired
	private MetricConverter metricConverter;

	@Override
	public List<Metric> findByDatasourceId(String datasourceUid) {
		return metricMetaRepository.findByDatasourceUid(datasourceUid);
	}

	@Override
	public Metric findByMetric(String metric) {
		return metricMetaRepository.findByMetric(metric);
	}

	@Override
	public void save(Metric metric) {
		final MetricMeta metricMeta = this.metricConverter.toMeta(metric);
		if (Objects.nonNull(metricMeta.getId())) {
			throw new IllegalArgumentException("save metric'id is not null");
		}
		metricMetaRepository.save(metricMeta);
	}

	@Override
	public void update(Metric metric) {
		final MetricMeta metricMeta = this.metricConverter.toMeta(metric);
		if (Objects.isNull(metricMeta.getId())) {
			throw new IllegalArgumentException("save metric'id is null");
		}
		metricMetaRepository.save(metricMeta);
	}

	@Override
	public Metric deleteById(Long id) {
		final Optional<MetricMeta> meta = metricMetaRepository.findById(id);
		meta.ifPresent(m -> metricMetaRepository.delete(m));
		if (!meta.isPresent()) {
			return null;
		}
		return metricConverter.fromMeta(meta.get());
	}

	@Override
	public List<Metric> getMetricsByCode(String code) {
		return metricMetaRepository.findByCode(code);
	}
}
