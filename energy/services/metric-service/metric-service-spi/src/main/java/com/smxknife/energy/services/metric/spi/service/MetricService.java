package com.smxknife.energy.services.metric.spi.service;

import com.smxknife.energy.services.metric.spi.domain.Metric;

import java.util.List;
import java.util.Optional;

/**
 * @author smxknife
 * 2021/5/17
 */
public interface MetricService {

	List<Metric> getMetricsByDatasource(String datasourceId);

	Optional<Metric> getByMetric(String metric);

	void create(Metric metric);

	void edit(Metric metric);

	Metric delete(Long id);

	List<Metric> getMetricsByCode(String code);
}
