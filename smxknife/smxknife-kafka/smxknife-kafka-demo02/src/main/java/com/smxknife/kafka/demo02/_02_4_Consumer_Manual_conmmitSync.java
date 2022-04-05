package com.smxknife.kafka.demo02;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author smxknife
 * 2020/8/31
 */
public class _02_4_Consumer_Manual_conmmitSync {
	public static void main(String[] args) {
		Properties properties = KafkaProperties.consumer();
		properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, _02_2_ConsumerInterceptor.class.getName());
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		System.out.println("====== 禁止了自动提交，采用手动同步提交 =======");
		String topic = "offset-test";
		consumer.subscribe(Collections.singleton(topic));

		while (true) {
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));

			for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
				System.out.println("-- consume record : value = " + consumerRecord.value() + " | offset = " + consumerRecord.offset());
				consumer.commitSync();// 这里每消费一条数据进行一次提交，虽然确保了安全性，但是吞吐量低，不适合生产环境
			}

		}
	}
}
