package com.smxknife.kafka.demo01;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author smxknife
 * 2020/8/29
 */
public class _01_4_KafkaProducer_ProtostuffSerializer {
	public static final String brokerList = "localhost:9092";
	public static final String topic = "demo01";
	public static final String groupId = "group.demo";

	public static Properties initConfig() {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ProtostuffSerializer.class.getName());
		props.put(ProducerConfig.CLIENT_ID_CONFIG, "xxxxxxxx-producer-test");
		return props;
	}

	public static void main(String[] args) {
		Properties properties = initConfig();
		KafkaProducer<String, _01_3_CustomObj> producer = new KafkaProducer<String, _01_3_CustomObj>(properties);

		ProducerRecord<String, _01_3_CustomObj> record = new ProducerRecord<>(topic, "key-1", new _01_3_CustomObj("hello", 10));

		producer.send(record);
		producer.close();
	}
}
