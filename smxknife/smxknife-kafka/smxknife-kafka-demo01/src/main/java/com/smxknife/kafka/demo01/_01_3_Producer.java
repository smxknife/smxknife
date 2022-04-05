package com.smxknife.kafka.demo01;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Objects;
import java.util.Properties;

/**
 * @author smxknife
 * 2020/8/24
 */
public class _01_3_Producer {

	static final String brokerList = "localhost:9092";
	static final String topic = "demo01";

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
		properties.put(ProducerConfig.CLIENT_ID_CONFIG, "customeSerializer");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, _01_3_CustomObjSerializer.class.getName());

		properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, _01_3_DemoPartitioner.class.getName());
		properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, _01_3_ProducerInterceptorPrefix.class.getName());

		System.out.println(_01_3_CustomObjSerializer.class.getCanonicalName());
		System.out.println(_01_3_CustomObjSerializer.class.getName());

		KafkaProducer<String, _01_3_CustomObj> producer = new KafkaProducer<String, _01_3_CustomObj>(properties);

		ProducerRecord<String, _01_3_CustomObj> record = new ProducerRecord<>(topic, "myKey", new _01_3_CustomObj("hello", 11));

		producer.send(record, new Callback() {
			@Override
			public void onCompletion(RecordMetadata recordMetadata, Exception e) {
				if (Objects.nonNull(e)) {
					e.printStackTrace();
				} else {
					System.out.println(recordMetadata);
				}
			}
		});

		producer.close();

	}




}
