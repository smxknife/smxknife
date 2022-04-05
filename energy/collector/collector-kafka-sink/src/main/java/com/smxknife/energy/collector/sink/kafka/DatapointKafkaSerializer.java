package com.smxknife.energy.collector.sink.kafka;

import com.smxknife.energy.common.util.ProtostuffUtil;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Objects;

/**
 * @author smxknife
 * 2021/5/14
 */
public class DatapointKafkaSerializer implements Serializer<Datapoint> {
	@Override
	public byte[] serialize(String topic, Datapoint datapoint) {

		if (Objects.isNull(datapoint)) {
			return null;
		}

		return ProtostuffUtil.serialize(datapoint);
	}
}
