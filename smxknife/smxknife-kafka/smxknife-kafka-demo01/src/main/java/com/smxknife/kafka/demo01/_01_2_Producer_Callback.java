package com.smxknife.kafka.demo01;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author smxknife
 * 2020/8/23
 */
public class _01_2_Producer_Callback {
	static final String brokerList = "localhost:9092";
	static final String topic = "demo01";

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("bootstrap.servers", brokerList);
		properties.put("client.id", "producer-demo01");
		// 配置生产者客户端参数并创建KafkaProducer实例
		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
		// 构建所需要发送的消息
		ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello, Kafka!");
		// 发送消息
		Future<RecordMetadata> recordMetadataFuture = kafkaProducer.send(record, new Callback() {
			@Override
			public void onCompletion(RecordMetadata recordMetadata, Exception e) {
				if (Objects.nonNull(e)) {
					e.printStackTrace();
				} else {
					System.out.println("callback " + recordMetadata);
					System.out.println("callback offset: " + recordMetadata.offset());
					System.out.println("callback topic: " + recordMetadata.topic());
					System.out.println("callback partition: " + recordMetadata.partition());
					System.out.println("callback timestamp: " + recordMetadata.timestamp());
				}
			}
		});
		try {
			RecordMetadata recordMetadata = recordMetadataFuture.get();
			System.out.println(recordMetadata);
			System.out.println("offset: " + recordMetadata.offset());
			System.out.println("topic: " + recordMetadata.topic());
			System.out.println("partition: " + recordMetadata.partition());
			System.out.println("timestamp: " + recordMetadata.timestamp());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		kafkaProducer.close();
	}
}
