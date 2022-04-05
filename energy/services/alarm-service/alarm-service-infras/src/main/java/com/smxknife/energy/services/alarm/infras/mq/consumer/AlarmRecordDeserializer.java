package com.smxknife.energy.services.alarm.infras.mq.consumer;

import com.smxknife.energy.common.util.ProtostuffUtil;
import com.smxknife.energy.services.alarm.spi.domain.AlarmRecord;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author smxknife
 * 2021/5/17
 */
public class AlarmRecordDeserializer implements Deserializer<AlarmRecord> {
	@Override
	public AlarmRecord deserialize(String topic, byte[] bytes) {
		return ProtostuffUtil.deserialize(AlarmRecord.class, bytes, new AlarmRecord());
	}
}
