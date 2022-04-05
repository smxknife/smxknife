package com.smxknife.energy.services.metric.infras.consumer;

import com.smxknife.energy.services.metric.core.dao.DatapointDao;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2021/5/17
 */
@Slf4j
@Service
public class DatapointConsumer extends Thread {

	@Autowired
	private DatapointConsumerProperties props;
	@Autowired
	private DatapointDao datapointDao;

	private KafkaConsumer<String, Datapoint> kafkaConsumer;

	private Map<TopicPartition, TopicPartitionConsumerThread> topicPartitionConsumerThreadMap = new ConcurrentHashMap<>();
	private BlockingQueue<ConsumerRecords<String, Datapoint>> recordsQueue = new LinkedBlockingQueue<>();

	@Override
	public void run() {

		while (true) {

			final ConsumerRecords<String, Datapoint> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(0));

			// 如果要手动提交offsest，因为consumer无法在多线程中共享，这里最好采用其他第三方分布式集群存储
			recordsQueue.add(consumerRecords);
			kafkaConsumer.commitSync();

			try {
				final ConsumerRecords<String, Datapoint> crs = recordsQueue.take();
				crs.partitions().stream().forEach(topicPartition -> {

					final TopicPartitionConsumerThread thread = topicPartitionConsumerThreadMap.computeIfAbsent(topicPartition, key -> {

						final TopicPartitionConsumerThread consumerThread = new TopicPartitionConsumerThread(
								key.topic(),
								key.partition(),
								datapointDao, new ConsumerCallback() {
							@Override
							public void onSuccess(String topic, int partition, long offset) {
								log.info("consumer topic = {}, partition = {}, offset = {} success", topic, partition, offset);
							}

							@Override
							public void onFail(String topic, int partition, long offset) {
								log.info("consumer topic = {}, partition = {}, offset = {} fail", topic, partition, offset);
							}
						});
						consumerThread.start();
						return consumerThread;
					});

					thread.appendRecords(crs.records(topicPartition));

				});
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	@PostConstruct
	private void init() {
		final Properties properties = new Properties();

		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, props.getBootstrapServers());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, props.getGroup());
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, DatapointDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

		kafkaConsumer = new KafkaConsumer<>(properties);

		kafkaConsumer.subscribe(Arrays.asList(props.getTopic()), new ConsumerRebalanceListener() {
			@Override
			public void onPartitionsRevoked(Collection<TopicPartition> collection) {
				final String collect = collection.stream()
						.map(TopicPartition::partition).map(String::valueOf).collect(Collectors.joining(", "));
				log.info(collect);
			}

			@Override
			public void onPartitionsAssigned(Collection<TopicPartition> collection) {
				final String collect = collection.stream()
						.map(TopicPartition::partition).map(String::valueOf).collect(Collectors.joining(", "));
				log.info(collect);
			}
		});
		this.start();
	}
}
