package com.smxknife.energy.collector.core.driver.sink;

import com.smxknife.energy.services.metric.spi.domain.Datapoint;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/12
 */
public interface SinkHandler {

	void handle(List<Datapoint> datapoints);
}
