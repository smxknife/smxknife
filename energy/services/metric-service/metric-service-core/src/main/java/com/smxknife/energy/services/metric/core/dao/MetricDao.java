package com.smxknife.energy.services.metric.core.dao;

import com.smxknife.energy.services.metric.spi.domain.Metric;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
public interface MetricDao {
	List<Metric> findByDatasourceId(String datasourceId);

	Metric findByMetric(String metric);

	void save(Metric metric);

	void update(Metric metric);

	Metric deleteById(Long id);

	List<Metric> getMetricsByCode(String code);
}
