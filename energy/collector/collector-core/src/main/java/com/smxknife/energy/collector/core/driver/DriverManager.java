package com.smxknife.energy.collector.core.driver;

import com.smxknife.energy.collector.core.driver.datasource.Datasource;
import com.smxknife.energy.collector.core.driver.datasource.DatasourceConverter;
import com.smxknife.energy.collector.core.driver.datasource.DatasourceHandler;
import com.smxknife.energy.collector.core.driver.sink.SinkHandler;
import com.smxknife.energy.collector.core.repository.DatasourceMetaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smxknife
 * 2021/5/12
 */
@Slf4j
@Component
public class DriverManager implements Lifecycle {

	@Autowired
	private DatasourceMetaRepository repository;
	@Autowired
	private DatasourceConverter dataSourceConverter;

	@Autowired
	private Map<String, DatasourceHandler> dataSourceHandlerMap = new ConcurrentHashMap<>();
	@Autowired
	private List<SinkHandler> sinkHandlers;

	private Map<String, Driver> driverMap = new ConcurrentHashMap<>();

	public void addDriver(Driver driver) {
		this.driverMap.putIfAbsent(driver.getUid(), driver);
	}

	@Override
	public void init() {
		repository.findAll().forEach(dsm -> {

			final Datasource dataSource = dataSourceConverter.fromMeta(dsm);

			final DatasourceHandler handler = dataSourceHandlerMap.get(dataSource.getType());
			handler.setDatasource(dataSource);

			final Driver driver = new Driver(handler, sinkHandlers);
			driver.init();
			this.addDriver(driver);
		});
	}

	@Override
	public void start() {
		driverMap.values().forEach(Driver::start);
	}

	@Override
	public void stop() {
		driverMap.values().forEach(Driver::start);
	}

	@Override
	public void terminate() {
		throw new UnsupportedOperationException();
	}
}
