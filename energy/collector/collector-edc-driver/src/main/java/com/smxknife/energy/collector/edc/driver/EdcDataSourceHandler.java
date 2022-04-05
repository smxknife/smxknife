package com.smxknife.energy.collector.edc.driver;

import com.smxknife.energy.collector.core.driver.datasource.AbstractDatasourceHandler;
import com.smxknife.energy.collector.core.driver.datasource.Datasource;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import com.smxknife.energy.services.metric.spi.domain.Metric;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2021/5/12
 */
@Component("EDC")
public class EdcDataSourceHandler extends AbstractDatasourceHandler {

	private Datasource dataSource;
	private Random random = new Random();
	private AtomicInteger integer = new AtomicInteger(0);

	@Override
	public Datasource getDatasource() {
		return this.dataSource;
	}

	@Override
	public void setDatasource(Datasource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void connect() {
		System.out.println("Edc DataSource Connect");
	}

	@Override
	public void disconnect() {
		System.out.println("Edc DataSource Connect");
	}

	@Override
	public List<Datapoint> doHandle(List<Metric> metrics, LocalDateTime dateTime, Map<String, Object> params) {
		System.out.println("handler xxxxx");
		final int base = integer.incrementAndGet();
		return metrics.stream().map(metric -> {
			return new Datapoint(metric.getMetric(),
					dateTime,
					base + random.nextDouble(),
					1);
		}).collect(Collectors.toList());
	}
}
