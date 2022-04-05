package com.smxknife.kafka.demo02;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @author smxknife
 * 2020/8/31
 */
public class _02_5_Consumer_seek {
	public static void main(String[] args) {
		Properties properties = KafkaProperties.consumer();
		properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, _02_2_ConsumerInterceptor.class.getName());
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		String topic = "offset-test";
		consumer.subscribe(Collections.singleton(topic));

		Set<TopicPartition> topicPartitions = new HashSet<>();

		while (topicPartitions.size() == 0) { // == 0说明还没有分配到分区，应该循环等到分区分配完成
			consumer.poll(Duration.ofSeconds(1));
			topicPartitions = consumer.assignment(); // 如果分区分配完了，那么这里就会返回
		}

		// 指定每个分区从第10个位置开始消费
		topicPartitions.forEach(p -> consumer.seek(p, 300));

		while (true) {
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
			for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
				System.out.println("consumerRecord offset = " + consumerRecord.offset() + " | value = " + consumerRecord.value());
			}
		}
	}
}
