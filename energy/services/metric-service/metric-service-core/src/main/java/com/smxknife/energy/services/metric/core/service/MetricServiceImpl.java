package com.smxknife.energy.services.metric.core.service;

import com.smxknife.energy.services.metric.core.dao.MetricDao;
import com.smxknife.energy.services.metric.spi.domain.Metric;
import com.smxknife.energy.services.metric.spi.service.MetricService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * @author smxknife
 * 2021/5/17
 */
@DubboService(version = "v1", interfaceClass = MetricService.class)
public class MetricServiceImpl implements MetricService {

	@Autowired
	private MetricDao metricDao;

	@Override
	public List<Metric> getMetricsByDatasource(String datasourceId) {
		return metricDao.findByDatasourceId(datasourceId);
	}

	@Override
	public Optional<Metric> getByMetric(String metric) {
		return Optional.ofNullable(metricDao.findByMetric(metric));
	}

	@Override
	public void create(Metric metric) {
		metricDao.save(metric);
	}

	@Override
	public void edit(Metric metric) {
		metricDao.update(metric);
	}

	@Override
	public Metric delete(Long id) {
		return metricDao.deleteById(id);
	}

	@Override
	public List<Metric> getMetricsByCode(String code) {
		return metricDao.getMetricsByCode(code);
	}
}
