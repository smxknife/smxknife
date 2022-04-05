package com.smxknife.energy.datacenter.core.consumer;

import com.smxknife.energy.common.util.ProtostuffUtil;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author smxknife
 * 2021/5/17
 */
public class EntEnergyDwsDataDeserializer implements Deserializer<Datapoint> {
	@Override
	public Datapoint deserialize(String topic, byte[] bytes) {
		return ProtostuffUtil.deserialize(Datapoint.class, bytes, new Datapoint());
	}
}
