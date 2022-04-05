package com.smxknife.kafka.demo02;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;

/**
 * @author smxknife
 * 2020/8/31
 */
public class _02_2_ConsumerInterceptor implements ConsumerInterceptor<String, String> {
	@Override
	public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> consumerRecords) {
		System.out.println("...onConsumer");
		return consumerRecords;
	}

	@Override
	public void onCommit(Map<TopicPartition, OffsetAndMetadata> map) {
		System.out.println("...onCommit | " + map);
	}

	@Override
	public void close() {
		System.out.println("...close");
	}

	@Override
	public void configure(Map<String, ?> map) {
		System.out.println("...configure | " + map);
	}
}
