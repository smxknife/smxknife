package com.smxknife.kafka.demo01;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author smxknife
 * 2020/8/29
 */
public class _01_4_KafkaConsumer {
	public static final String brokerList = "localhost:9092";
	public static final String topic = "demo01";
	public static final String groupId = "group.demo";
	public static final AtomicBoolean isRunning = new AtomicBoolean(true);

	public static Properties initConfig() {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, "xxxxxxxx-test");
		return props;
	}

	public static void main(String[] args) {
		Properties properties = initConfig();
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(Collections.singleton(topic));

		try {
			while (isRunning.get()) {
				ConsumerRecords<String, String> records = consumer.poll(1000);
				for (ConsumerRecord<String, String> record : records) {
					System.out.println("topic = " + record.topic() + ", partition = " + record.partition() + ", offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			consumer.close();
		}
	}
}
