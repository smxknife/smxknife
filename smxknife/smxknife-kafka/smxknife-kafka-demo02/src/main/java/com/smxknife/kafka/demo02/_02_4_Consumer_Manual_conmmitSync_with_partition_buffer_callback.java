package com.smxknife.kafka.demo02;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smxknife
 * 2020/8/31
 */
public class _02_4_Consumer_Manual_conmmitSync_with_partition_buffer_callback {
	public static void main(String[] args) {
		Properties properties = KafkaProperties.consumer();
		properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, _02_2_ConsumerInterceptor.class.getName());
		properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		System.out.println("====== 禁止了自动提交，采用手动同步提交 (按分区粒度批量提交) =======");
		String topic = "offset-test";
		consumer.subscribe(Collections.singleton(topic));

		int minBatchCommitSize = 10;
		Map<TopicPartition, Integer> partitionCounterMap = new ConcurrentHashMap<>();

		while (true) {
			ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));

			Set<TopicPartition> partitions = consumerRecords.partitions();

			partitions.forEach(partition -> {
				List<ConsumerRecord<String, String>> records = consumerRecords.records(partition);

				records.forEach(record -> {
					System.out.println("-- consume record : value = " + record.value() + " | offset = " + record.offset());

					Integer counter = partitionCounterMap.compute(partition, (key, val) -> {
						int count = 1;
						if (Objects.nonNull(val)) {
							count = val + 1;
						}
						return count;
					});


					System.out.println(">>>>>>>>>>>>>>>>> " + counter);

					long lastOffset = records.get(records.size() - 1).offset();

					//records
					if (Objects.nonNull(counter) && counter.intValue() >= minBatchCommitSize) {
						System.out.println(partition + " counter = " + counter.intValue());
						OffsetAndMetadata offsetAndMetadata = new OffsetAndMetadata(lastOffset + 1);
						consumer.commitSync(Collections.singletonMap(partition, offsetAndMetadata));
						partitionCounterMap.put(partition, 0);
					}

				});
			});

		}
	}
}
