package com.smxknife.kafka.demo02;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给消息设置过期时间
 * @author smxknife
 * 2020/8/31
 */
public class _02_9_ConsumerInterceptorTTL implements ConsumerInterceptor<String, String> {

	private static final long EXPIRE_INTERVAL = 10 * 1000;


	@Override
	public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> consumerRecords) {
		System.out.println("...onConsumer");

		final long now = System.currentTimeMillis();
		Map<TopicPartition, List<ConsumerRecord<String, String>>> newConsumerRecords = new HashMap<>();
		consumerRecords.partitions().forEach(tp -> {
			final List<ConsumerRecord<String, String>> records = consumerRecords.records(tp);
			final List<ConsumerRecord<String, String>> newRecords = newConsumerRecords.get(tp);
			records.forEach(rd -> {
				// 注意这里，每一个ConsumerRecord都有一个timestamp，那么在消费的时候，可以与当前的时间进行判断，如果超过了
				// 最大时间，判断为过期了
				final long timestamp = rd.timestamp();
				if ((now - timestamp) < EXPIRE_INTERVAL) {
					newRecords.add(rd);
				}
				if (!newRecords.isEmpty()) {
					newConsumerRecords.put(tp, newRecords);
				}
			});

		});
		return new ConsumerRecords<>(newConsumerRecords);
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
