package com.smxknife.kafka.demo01;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author smxknife
 * 2020/8/23
 */
public class _01_1_Consumer {
	static final String brokerList = "localhost:9092";
	static final String topic = "demo01";
	static final String groupId = "group.demo01";

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("bootstrap.servers", brokerList);
		properties.put("group.id", groupId);
		// 创建一个消费者客户端实例
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(Collections.singletonList(topic));
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(1000);
			for (ConsumerRecord<String, String> record : records) {
				System.out.println(record.value());
			}
		}
	}
}
