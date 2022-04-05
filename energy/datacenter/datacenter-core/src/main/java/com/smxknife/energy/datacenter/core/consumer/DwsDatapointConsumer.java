package com.smxknife.energy.datacenter.core.consumer;

import com.smxknife.energy.datacenter.spi.domain.EntEnergyDwsData;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.Duration;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * 负责将DWS数据从kafka进行获取，然后落库
 * @author smxknife
 * 2021/5/20
 */
@Slf4j
@Component
public class DwsDatapointConsumer extends Thread {

	@Value("${datacenter.ent.energy.dws.table:EntEnergyDwsData}")
	private String table;

	@Autowired
	private Connection hbaseConnection;

	@Autowired
	private DwsConsumerProperties props;

	private KafkaConsumer<String, EntEnergyDwsData> kafkaConsumer;

	@Override
	public void run() {
		while (true) {
			final ConsumerRecords<String, EntEnergyDwsData> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(0));

			consumerRecords.partitions().forEach(topicPartition -> {
				final List<ConsumerRecord<String, EntEnergyDwsData>> records = consumerRecords.records(topicPartition);
				final List<EntEnergyDwsData> recordList = records.stream().map(v -> v.value()).collect(Collectors.toList());
				// TODO 这里保存到hbase
				try {
					final Table table = hbaseConnection.getTable(TableName.valueOf("EntEnergyDwsData"));

					final List<Put> puts = recordList.stream().map(dws -> {
						Put put = new Put(Bytes.toBytes("rowKey" + ThreadLocalRandom.current().nextInt(1000000)));
						// EntInfo
						put.addColumn(Bytes.toBytes("EntInfo"), Bytes.toBytes("entCode"), Bytes.toBytes(dws.getEntCode()));
						// EnergyInfo
						put.addColumn(Bytes.toBytes("EnergyInfo"), Bytes.toBytes("energyCode"), Bytes.toBytes(dws.getEnergyCode()));
						put.addColumn(Bytes.toBytes("EnergyInfo"), Bytes.toBytes("dataCode"), Bytes.toBytes(dws.getDataCode()));
						put.addColumn(Bytes.toBytes("EnergyInfo"), Bytes.toBytes("dataValue"), Bytes.toBytes(dws.getDataValue()));
						put.addColumn(Bytes.toBytes("EnergyInfo"), Bytes.toBytes("dateTime"), Bytes.toBytes(dws.getDateTime().atZone(ZoneId.systemDefault()).toEpochSecond()));
						// IndustryInfo
						put.addColumn(Bytes.toBytes("IndustryInfo"), Bytes.toBytes("industryCode"), Bytes.toBytes(dws.getIndustryCode()));
						// RegionInfo
						put.addColumn(Bytes.toBytes("RegionInfo"), Bytes.toBytes("regionCode"), Bytes.toBytes(dws.getRegionCode()));

						return put;
					}).collect(Collectors.toList());

					table.put(puts);

				} catch (IOException e) {
					e.printStackTrace();
				}

			});
		}
	}

	@PostConstruct
	public void init() {
		final Properties properties = new Properties();

		properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, props.getBootstrapServers());
		properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, props.getGroup());
		properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, EntEnergyDwsDataDeserializer.class.getName());
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
