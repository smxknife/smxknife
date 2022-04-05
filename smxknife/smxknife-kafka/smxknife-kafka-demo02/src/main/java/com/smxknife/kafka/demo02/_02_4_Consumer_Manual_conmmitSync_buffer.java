package com.smxknife.kafka.demo02;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smxknife
 * 2020/8/31
 */
public class _02_4_Consumer_Manual_conmmitSync_buffer {
	public static void main(String[] args) {
		Properties properties = KafkaProperties.consumer();
		properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, _02_2_ConsumerInterceptor.class.getName());
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		System.out.println("====== 禁止了自动提交，采用手动同步提交 (批量提交) =======");
		String topic = "offset-test";
		consumer.subscribe(Collections.singleton(topic));

		int minBatchCommitSize = 10;
		AtomicInteger counter = new AtomicInteger(0);
		while (true) {
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));

			for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
				System.out.println("-- consume record : value = " + consumerRecord.value() + " | offset = " + consumerRecord.offset());

				if (counter.incrementAndGet() >= minBatchCommitSize) {
					// 这里采用批量提交的方式，每消费10条消息进行一次提交，虽然吞吐量提升了，但是如果发生异常了，那么会出现重复消费一整个批次的数据
					System.out.println("counter = " + counter.get());
					consumer.commitSync();
					counter.set(0);
				}

			}

		}
	}
}
