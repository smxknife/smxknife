package com.smxknife.kafka.demo02;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author smxknife
 * 2020/8/30
 */
public class _02_1_Consumer {

	public static void main(String[] args) {

		String topic = "offset-test";

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(KafkaProperties.consumer());

		List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
		System.out.println(topic + " | 主题分区信息如下：");
		partitionInfos.forEach(partitionInfo -> {
			System.out.println(String.format("  * topic: %s | partition: %s | leader: %s | ", partitionInfo.topic(), partitionInfo.partition(), partitionInfo.leader().id()));
		});
		System.out.println("=======================");

		TopicPartition tp = new TopicPartition(topic, 0);
		consumer.assign(Collections.singleton(tp));

		// 当前消费到的位移
		long lastConsumerOffset = -1;

		while (true) {
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
			if (consumerRecords.isEmpty()) {
				System.out.println(String.format("%s is empty", topic));
				break;
			}

			List<ConsumerRecord<String, String>> records = consumerRecords.records(tp);
			System.out.println("--- 批量获取的消息如下：");
			records.forEach(record -> {
				System.out.println(String.format("    * Record | topic = %s, partition = %s, offset = %s, key = %s, value = %s", record.topic(), record.partition(), record.offset(), record.key(), record.value()));
			});
			System.out.println("--------------------------------");

			lastConsumerOffset = records.get(records.size() - 1).offset();
			System.out.println("lastConsumerOffset = " + lastConsumerOffset);

			consumer.commitSync();
		}

		System.out.println("consumed offset is " + lastConsumerOffset);
		Map<TopicPartition, OffsetAndMetadata> offsetAndMetadataMap = consumer.committed(Collections.singleton(tp));
		System.out.println("commited offset is " + offsetAndMetadataMap.get(tp));
		long position = consumer.position(tp);
		System.out.println("the offset of the next record is " + position);

	}
}
