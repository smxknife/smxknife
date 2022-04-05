package com.smxknife.kafka.demo02;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/8/31
 */
public class _02_3_Consumer_Mock_LostData {
	public static void main(String[] args) throws InterruptedException {
		Properties properties = KafkaProperties.consumer();
		properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, _02_2_ConsumerInterceptor.class.getName());
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		String topic = "offset-test";
		consumer.subscribe(Collections.singleton(topic));

		BlockingDeque<String> deque = new LinkedBlockingDeque<>();

		new Thread(() -> {
			// 该线程模拟业务消费数据，消费速度较慢，消费者不断循环poll操作，拉取了大量的数据，每次执行poll都会进行自动提交offset操作
			// offset已经提交了，但是拉取的数据还没有被真正的业务系统消费，如果业务系统出现异常，那么下次重启时就会从新的offset的位置拉取数据
			// 业务系统还没来的及处理的数据（deque中的数据）因为重启而丢失了，那么就造成了数据丢失
			while(true) {
				if (deque.size() > 20) {
					throw new RuntimeException("too large exception");
				}
				try {
					String message = deque.take();
					System.out.println(" ** handle message " + message);
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start();

		while (true) {
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));

			for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
				System.out.println("-- receive record : value = " + consumerRecord.value() + " | offset = " + consumerRecord.offset());
				deque.offer(consumerRecord.value());
			}

		}


	}
}
