package com.smxknife.kafka.demo02;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * @author smxknife
 * 2020/8/31
 */
public class _02_5_Consumer_assign {
	public static void main(String[] args) {
		Properties properties = KafkaProperties.consumer();
//		properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, _02_2_ConsumerInterceptor.class.getName());
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		String topic = "assign-test";

		Set<TopicPartition> partitions = new HashSet<>();

		while (partitions.isEmpty()) {
			final List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
			partitionInfos.forEach(partitionInfo -> {
				TopicPartition topicPartition = new TopicPartition(partitionInfo.topic(), partitionInfo.partition());
				partitions.add(topicPartition);
			});
		}

		consumer.assign(partitions);
		while (true) {
			final ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(100));
			for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
				System.out.println(consumerRecord.offset() + " | " + consumerRecord.partition() + " | " + consumerRecord.value());
			}
		}


	}
}
