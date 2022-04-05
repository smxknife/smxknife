package com.smxknife.kafka.demo03;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2021/5/11
 */
public class Lesson01 {

	@Test
	public void producer() throws ExecutionException, InterruptedException {
		final Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"192.168.218.23:9092,192.168.218.24:9092,192.168.218.25:9092");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		final KafkaProducer<String, String> producer = new KafkaProducer(props);
		// 现在的produer面向的是一个broker，没有任何的topic

		/**
		 * topic：ooxx
		 * - 2 partition
		 * 三种商品，每种商品有三个线性ID
		 * - 相同的商品最好去一个分区里
		 */

		String topic = "ooxx";
		while (true) {
			// 三个id
			for (int id = 0; id < 3; id++) {
				// 三个商品
				for (int item = 0; item < 3; item++) {
					String key = "item" + item;
					String value = id + "";
					final Future<RecordMetadata> metadataFuture = producer.send(new ProducerRecord<String, String>(topic, key, value));

					// 这里的操作是阻塞的
					final RecordMetadata recordMetadata = metadataFuture.get();
					System.out.println("key: " + key + ", value: " + value + ", partition: " + recordMetadata.partition() + ", offset : " + recordMetadata.offset());
				}
			}
		}

	}

	@Test
	public void consumer() {

		final Properties props = new Properties();
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"192.168.218.23:9092,192.168.218.24:9092,192.168.218.25:9092");
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		// 消费者有组的概念
		props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "c_1");
		// 从哪里开始消费
		/**
		 * auto.offset.reset的可选值
		 * - earliest：从最早的开始
		 * - latest：默认，从最后一条开始
		 * - none：如果没有之前的offset会抛出异常
		 * - anything else：？
		 */
		props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
		// 开启offset自动提交，默认true
		// 这种自动提交是异步提交，这就有可能两种情况
		// - 1. 消息拉取后，还没业务处理已经提交了
		// - 2. 消息拉取后，如果处理过快，offset还没来得及提交，会出现重复消费数据
		// ：：异步提交默认的间隔是5s
		// 所以，自动提交很容易出现重复数据和丢数据
		props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
		// 这里可以配置自动提交的时间间隔
//		props.setProperty(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, );

		// consumer：POLL 拉取数据，弹性的按需的，拉取多少
//		props.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, )


		final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

		// 订阅topic
		// 1。1这是一种简单的方式
//		consumer.subscribe(Arrays.asList("ooxx"));
		// 1。2这是另外一种方式，根据consumer的增减，动态的负载均衡
		consumer.subscribe(Arrays.asList("ooxx"), new ConsumerRebalanceListener() {
			@Override
			public void onPartitionsRevoked(Collection<TopicPartition> collection) {
				// 被移走的分区有哪些
				final String collect = collection.stream()
						.map(TopicPartition::partition).map(String::valueOf).collect(Collectors.joining(", "));
				System.out.println("------ onPartitionsRevoked ----------- | " + collect);
			}

			@Override
			public void onPartitionsAssigned(Collection<TopicPartition> collection) {
				// 被分配了哪些分区
				final String collect = collection.stream()
						.map(TopicPartition::partition).map(String::valueOf).collect(Collectors.joining(", "));
				System.out.println("------ onPartitionsAssigned ----------- | " + collect);
			}
		});

		while (true) {
			// POLL
			final ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(0));

			if (consumerRecords.isEmpty()) {
				continue;
			}

			System.out.println("------------------ " + consumerRecords.count() + " -------------------");

			// 以下代码优化很重要
			final Iterator<ConsumerRecord<String, String>> iterator = consumerRecords.iterator();
			while (iterator.hasNext()) {
				// 一个consumer可以消费多个分区，但是一个分区只能给一个consumer
				final ConsumerRecord<String, String> record = iterator.next();

				System.out.println("key: " + record.key() + ", value: " + record.value() + ", partition: " + record.partition() + ", offset: " + record.offset());
			}
		}

	}

	@Test
	public void consumerDeliverByPartition() {

		final Properties props = new Properties();
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"192.168.218.23:9092,192.168.218.24:9092,192.168.218.25:9092");
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "c_1");

		props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

		props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");


		final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

		consumer.subscribe(Arrays.asList("ooxx"), new ConsumerRebalanceListener() {
			@Override
			public void onPartitionsRevoked(Collection<TopicPartition> collection) {
				final String collect = collection.stream()
						.map(TopicPartition::partition).map(String::valueOf).collect(Collectors.joining(", "));
				System.out.println("------ onPartitionsRevoked ----------- | " + collect);
			}

			@Override
			public void onPartitionsAssigned(Collection<TopicPartition> collection) {
				final String collect = collection.stream()
						.map(TopicPartition::partition).map(String::valueOf).collect(Collectors.joining(", "));
				System.out.println("------ onPartitionsAssigned ----------- | " + collect);
			}
		});

		while (true) {
			// POLL
			final ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(0));

			if (consumerRecords.isEmpty()) {
				continue;
			}

			System.out.println("------------------ " + consumerRecords.count() + " -------------------");

			// 从这里可以看出，一次拉取是从多个分区拉取
			final Set<TopicPartition> partitions = consumerRecords.partitions();
			// 分区内有序，分区外无序，所以可以单线程处理不同分区，也可以多线程处理不同的分区
			// 2。2单线程版
			partitions.forEach(partition -> {
				final List<ConsumerRecord<String, String>> records = consumerRecords.records(partition);
				printConsumerRecord((Iterator<ConsumerRecord<String, String>>) records);
			});

			// 2。3多线程版
//			partitions.forEach(partition -> {
//				new Thread(() -> {
//					final List<ConsumerRecord<String, String>> records = consumerRecords.records(partition);
//					printConsumerRecord((Iterator<ConsumerRecord<String, String>>) records);
//				}).start();
//
//			});


		}

	}

	@Test
	public void consumerOffsetCommitManual() {

		final Properties props = new Properties();
		props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				"192.168.218.23:9092,192.168.218.24:9092,192.168.218.25:9092");
		props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

		props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "c_1");

		props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");

		props.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");


		final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

		consumer.subscribe(Arrays.asList("ooxx"), new ConsumerRebalanceListener() {
			@Override
			public void onPartitionsRevoked(Collection<TopicPartition> collection) {
				final String collect = collection.stream()
						.map(TopicPartition::partition).map(String::valueOf).collect(Collectors.joining(", "));
				System.out.println("------ onPartitionsRevoked ----------- | " + collect);
			}

			@Override
			public void onPartitionsAssigned(Collection<TopicPartition> collection) {
				final String collect = collection.stream()
						.map(TopicPartition::partition).map(String::valueOf).collect(Collectors.joining(", "));
				System.out.println("------ onPartitionsAssigned ----------- | " + collect);
			}
		});

		while (true) {
			// POLL
			final ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(0));

			if (consumerRecords.isEmpty()) {
				continue;
			}

			System.out.println("------------------ " + consumerRecords.count() + " -------------------");

			final Set<TopicPartition> partitions = consumerRecords.partitions();

			/**
			 * 因为自动提交改为false，所以需要手动提交
			 * 3。1按消息进度同步提交（处理一条提交一条）
			 * 3。2按分区粒度同步提交
			 * 3。3按当前poll的批次同步
			 *
			 * 思考：多线程环境
			 * 1。 以上3。1不可用于多线程
			 * 2。 以上3。2多适合与多线程
			 *
 			 */


			// 2。2单线程版
			partitions.forEach(partition -> {
				final List<ConsumerRecord<String, String>> records = consumerRecords.records(partition);
				try {
					printConsumerRecord((Iterator<ConsumerRecord<String, String>>) records);
				} catch (Exception e) {
					e.printStackTrace();
					consumer.commitSync();
				}
			});

			// 2。3多线程版
//			partitions.forEach(partition -> {
//				new Thread(() -> {
//					final List<ConsumerRecord<String, String>> records = consumerRecords.records(partition);
//					printConsumerRecord((Iterator<ConsumerRecord<String, String>>) records);
//				}).start();
//
//			});


		}

	}


	private void printConsumerRecord(Iterator<ConsumerRecord<String, String>> iterator) {
		while (iterator.hasNext()) {
			// 一个consumer可以消费多个分区，但是一个分区只能给一个consumer
			final ConsumerRecord<String, String> record = iterator.next();

			System.out.println("key: " + record.key() + ", value: " + record.value() + ", partition: " + record.partition() + ", offset: " + record.offset());
		}
	}
}
