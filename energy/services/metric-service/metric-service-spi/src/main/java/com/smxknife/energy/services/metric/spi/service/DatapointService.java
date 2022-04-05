package com.smxknife.energy.services.metric.spi.service;

import com.smxknife.energy.services.metric.spi.domain.Datapoint;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author smxknife
 * 2021/5/17
 */
public interface DatapointService {

	Datapoint read(String metric, LocalDateTime dateTime, boolean isOk);

	List<Datapoint> read(String metric, LocalDateTime startTime, LocalDateTime endTime, boolean isOk);

	boolean write(Datapoint datapoint);

	boolean write(List<Datapoint> datapoints);

}
