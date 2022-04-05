package com.smxknife.energy.collector.core.driver.datasource;

import com.smxknife.energy.collector.core.driver.sink.SinkHandler;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import com.smxknife.energy.services.metric.spi.domain.Metric;
import com.smxknife.energy.services.metric.spi.service.MetricService;
import org.apache.dubbo.config.annotation.DubboReference;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author smxknife
 * 2021/5/14
 */
public abstract class AbstractDatasourceHandler implements DatasourceHandler {

	@DubboReference(version = "v1")
	private MetricService metricService;

	@Override
	public void handle(LocalDateTime dateTime, Map<String, Object> params, List<SinkHandler> sinkHandlers) {
		final List<Metric> metrics = metricService.getMetricsByDatasource(getDatasource().getUid());
		sinkHandlers.forEach(sink -> sink.handle(doHandle(metrics, dateTime, params)));
	}

	protected abstract List<Datapoint> doHandle(List<Metric> metrics, LocalDateTime dateTime, Map<String, Object> params);
}
