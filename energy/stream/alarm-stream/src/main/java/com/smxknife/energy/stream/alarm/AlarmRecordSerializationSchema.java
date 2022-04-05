package com.smxknife.energy.stream.alarm;

import com.smxknife.energy.common.util.ProtostuffUtil;
import com.smxknife.energy.services.alarm.spi.domain.AlarmRecord;
import org.apache.flink.streaming.connectors.kafka.KafkaSerializationSchema;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.annotation.Nullable;
import java.nio.charset.StandardCharsets;

/**
 * @author smxknife
 * 2021/5/18
 */
public class AlarmRecordSerializationSchema implements KafkaSerializationSchema<AlarmRecord> {

	@Override
	public ProducerRecord<byte[], byte[]> serialize(AlarmRecord alarmRecord, @Nullable Long aLong) {
		return new ProducerRecord<byte[], byte[]>("alarmRecords",
				alarmRecord.getRuleUid().getBytes(StandardCharsets.UTF_8), ProtostuffUtil.serialize(alarmRecord));
	}
}
