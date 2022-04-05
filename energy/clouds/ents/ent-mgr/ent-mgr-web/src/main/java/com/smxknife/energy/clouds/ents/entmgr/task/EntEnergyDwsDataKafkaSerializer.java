package com.smxknife.energy.clouds.ents.entmgr.task;

import com.smxknife.energy.common.util.ProtostuffUtil;
import com.smxknife.energy.datacenter.spi.domain.EntEnergyDwsData;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Objects;

/**
 * @author smxknife
 * 2021/5/14
 */
public class EntEnergyDwsDataKafkaSerializer implements Serializer<EntEnergyDwsData> {
	@Override
	public byte[] serialize(String topic, EntEnergyDwsData dwsData) {

		if (Objects.isNull(dwsData)) {
			return null;
		}

		return ProtostuffUtil.serialize(dwsData);
	}
}
