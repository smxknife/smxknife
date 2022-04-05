package com.smxknife.energy.services.alarm.infras.mq.producer;

import com.smxknife.energy.common.util.ProtostuffUtil;
import com.smxknife.energy.services.alarm.spi.domain.AlarmRule;
import org.apache.kafka.common.serialization.Serializer;

/**
 * @author smxknife
 * 2021/5/17
 */
public class AlarmRuleWrapperKafkaSerializer implements Serializer<AlarmRule> {

	@Override
	public byte[] serialize(String s, AlarmRule rule) {
		return ProtostuffUtil.serialize(rule);
	}
}
