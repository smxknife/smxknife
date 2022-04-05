package com.smxknife.kafka.demo02;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author smxknife
 * 2020/8/30
 */
public class _02_1_Producer {
	public static void main(String[] args) {

		String topic = "offset-test";

		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(KafkaProperties.producer());

		ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello, offset");
		producer.send(record);
		producer.close();
	}
}
