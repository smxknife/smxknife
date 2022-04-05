package com.smxknife.kafka.demo01;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author smxknife
 * 2020/8/24
 */
public class _01_3_Consumer {

	static final String brokerList = "localhost:9092";
	static final String topic = "demo01";

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "customeSerializer");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, _01_3_CustomObjDeserializer.class.getName());

		KafkaConsumer<String, _01_3_CustomObj> consumer = new KafkaConsumer<String, _01_3_CustomObj>(properties);

		consumer.subscribe(Collections.singletonList(topic));

		while (true) {
			ConsumerRecords<String, _01_3_CustomObj> records = consumer.poll(1000);
			for (ConsumerRecord<String, _01_3_CustomObj> record : records) {
				System.out.println(record.key() + " | " + record.value());
			}
		}
	}




}
