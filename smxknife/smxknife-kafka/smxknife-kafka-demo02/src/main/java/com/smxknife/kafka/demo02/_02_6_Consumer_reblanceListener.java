package com.smxknife.kafka.demo02;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

/**
 * @author smxknife
 * 2020/8/31
 */
public class _02_6_Consumer_reblanceListener {
	public static void main(String[] args) {
		Properties properties = KafkaProperties.consumer();
		properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, _02_2_ConsumerInterceptor.class.getName());
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		String topic = "offset-test";
		consumer.subscribe(Collections.singleton(topic), new ConsumerRebalanceListener() {
			@Override
			public void onPartitionsRevoked(Collection<TopicPartition> collection) {
				System.out.println("...onPartitionsRevoked...再均衡开始之前喝消费者停止读取消息之后被调用");
			}

			@Override
			public void onPartitionsAssigned(Collection<TopicPartition> collection) {
				System.out.println("...onPartitionsAssigned...重新分区之后和消费者开始读消息之前被调用");
			}
		});

		while (true) {
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
			for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
				System.out.println(consumerRecord.value());
			}
		}
	}
}
