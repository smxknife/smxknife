package com.smxknife.kafka.demo01;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;
import java.util.Objects;

/**
 * @author smxknife
 * 2020/8/29
 */
public class _01_3_ProducerInterceptorPrefix implements ProducerInterceptor<String, _01_3_CustomObj> {

	private volatile long sendSuccess = 0;
	private volatile long sendFailure = 0;

	@Override
	public ProducerRecord<String, _01_3_CustomObj> onSend(ProducerRecord<String, _01_3_CustomObj> record) {
		System.out.println("ProducerInterceptor onSend...");
		_01_3_CustomObj value = record.value();
		value.setName("prefix-" + value.getName());
		return new ProducerRecord<>(record.topic(), record.partition(), record.timestamp(), record.key(), value, record.headers());
	}

	@Override
	public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
		System.out.println("ProducerInterceptor onAcknowledgement...");
		if (Objects.isNull(e)) {
			sendSuccess++;
		} else {
			sendFailure++;
		}
	}

	@Override
	public void close() {
		System.out.println("ProducerInterceptor close...");
		double successRatio = (double) sendSuccess / (sendFailure + sendSuccess);
		System.out.println("发送成功率=" + successRatio * 100 + "%");
	}

	@Override
	public void configure(Map<String, ?> map) {
		System.out.println("ProducerInterceptor configure...");
	}
}
