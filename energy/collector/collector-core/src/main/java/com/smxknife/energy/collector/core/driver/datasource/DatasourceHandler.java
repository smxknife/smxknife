package com.smxknife.energy.collector.core.driver.datasource;

import com.smxknife.energy.collector.core.driver.sink.SinkHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author smxknife
 * 2021/5/12
 */
public interface DatasourceHandler {

	Datasource getDatasource();
	void setDatasource(Datasource dataSource);

	void connect();
	void disconnect();

	void handle(LocalDateTime dateTime, Map<String, Object> params, List<SinkHandler> sinkHandlers);
}
