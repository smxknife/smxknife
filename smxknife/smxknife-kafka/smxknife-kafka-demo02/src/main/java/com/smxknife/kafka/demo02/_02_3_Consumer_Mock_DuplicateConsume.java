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
public class _02_3_Consumer_Mock_DuplicateConsume {
	public static void main(String[] args) throws InterruptedException {
		Properties properties = KafkaProperties.consumer();
		properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, _02_2_ConsumerInterceptor.class.getName());
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		String topic = "offset-test";
		consumer.subscribe(Collections.singleton(topic));

		while (true) {
			// 每次poll都会执行commit offset，所以，如果在while true循环中进行业务处理会导致如果发生异常，会重新拉取上一次的offset
			// 因为本次拉取的offset虽然已经消费了一部分，还有一部分没有消费完毕，但是本次循环还没有结束，无法进行下一次的poll，也就是说无法进行提交操作
			// 那么在下次重启的时候，会重新拉取上次拉取数据的offset，也就是说上次消费过的数据，会重新消费一边
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));

			int i = 0;
			for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
				System.out.println("-- consume record : value = " + consumerRecord.value() + " | offset = " + consumerRecord.offset());
				i++;

				if (i % 5 == 0) {
					throw new RuntimeException();
				}
			}

		}


	}
}
