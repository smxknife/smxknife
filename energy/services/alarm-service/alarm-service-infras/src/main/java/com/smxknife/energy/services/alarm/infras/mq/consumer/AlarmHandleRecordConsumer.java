package com.smxknife.energy.services.alarm.infras.mq.consumer;

import com.smxknife.energy.services.alarm.infras.converter.AlarmHandleRecordConverter;
import com.smxknife.energy.services.alarm.infras.entity.AlarmHandleRecordMetaRepository;
import com.smxknife.energy.services.alarm.spi.domain.AlarmHandleRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2021/5/17
 */
@Slf4j
@Component
public class AlarmHandleRecordConsumer extends Thread {

	@Autowired
	private AlarmHandleRecordConsumerProperties props;

	private KafkaConsumer<String, AlarmHandleRecord> kafkaConsumer;

	@Autowired
	private AlarmHandleRecordMetaRepository repository;

	@Autowired
	private AlarmHandleRecordConverter converter;


	@Override
	public void run() {
		while (true) {
			final ConsumerRecords<String, AlarmHandleRecord> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(0));

			consumerRecords.partitions().forEach(topicPartition -> {
				final List<ConsumerRecord<String, AlarmHandleRecord>> records = consumerRecords.records(topicPartition);
				final List<AlarmHandleRecord> recordList = records.stream().map(v -> v.value()).collect(Collectors.toList());
				repository.saveAll(converter.toMetas(recordList));
			});
		}
	}

	@PostConstruct
	public void init() {
		final Properties properties = new Properties();

		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, props.getBootstrapServers());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, props.getGroup());
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, AlarmRecordDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		// TODO 测试，这里不管了
//		properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

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
