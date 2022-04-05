package com.smxknife.energy.services.alarm.infras.mq.consumer;

import com.smxknife.energy.common.util.ProtostuffUtil;
import com.smxknife.energy.services.alarm.spi.domain.AlarmHandleRecord;
import org.apache.kafka.common.serialization.Deserializer;

/**
 * @author smxknife
 * 2021/5/17
 */
public class AlarmHandleRecordDeserializer implements Deserializer<AlarmHandleRecord> {
	@Override
	public AlarmHandleRecord deserialize(String topic, byte[] bytes) {
		return ProtostuffUtil.deserialize(AlarmHandleRecord.class, bytes, new AlarmHandleRecord());
	}
}
