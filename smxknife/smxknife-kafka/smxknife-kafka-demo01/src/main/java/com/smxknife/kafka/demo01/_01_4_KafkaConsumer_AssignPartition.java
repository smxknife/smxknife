package com.smxknife.kafka.demo01;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2020/8/29
 */
public class _01_4_KafkaConsumer_AssignPartition {
	public static final String brokerList = "localhost:9092";
	public static final String topic = "demo01";
	public static final String groupId = "group.demo";
	public static final AtomicBoolean isRunning = new AtomicBoolean(true);

	public static Properties initConfig() {
		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, "xxxxxxxx-test-with-partition");
		return props;
	}

	public static void main(String[] args) {
		Properties properties = initConfig();
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		// 从topic获取主题分区列表，从主题的元数据信息
		List<PartitionInfo> partitions = consumer.partitionsFor(topic);
		// 将List<PartitionInfo>转化为Map<Integer, TopicPartition>
		//  * 转化成map只是为了方便根据分区号来获取相应的TopicPartition
		//  * 转化主要是将PartitionInfo转化为TopicPartition
		Map<Integer, TopicPartition> topicPartitionMap = partitions.stream().collect(Collectors.toMap(PartitionInfo::partition, p -> new TopicPartition(p.topic(), p.partition())));

		// 1.1 如果要发到指定的分区，那么就先判断分区是否存在，如果存在就获取相应的TopicPartition
		/**
		TopicPartition topicPartition = topicPartitionMap.get(0);
		if (Objects.nonNull(topicPartition)) {
			consumer.assign(Collections.singleton(topicPartition));
		}
		*/

		// 1.2 可以指定多个集合
		consumer.assign(topicPartitionMap.values());


		try {
			while (isRunning.get()) {
				ConsumerRecords<String, String> records = consumer.poll(1000);
				for (ConsumerRecord<String, String> record : records) {
					System.out.println("topic = " + record.topic() + ", partition = " + record.partition() + ", offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			consumer.close();
		}
	}
}
