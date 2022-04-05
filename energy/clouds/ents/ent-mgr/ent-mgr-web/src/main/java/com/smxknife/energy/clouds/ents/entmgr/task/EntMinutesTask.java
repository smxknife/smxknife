package com.smxknife.energy.clouds.ents.entmgr.task;

import com.smxknife.energy.clouds.ents.entmgr.config.KafkaDwsProperties;
import com.smxknife.energy.clouds.ents.entmgr.entity.EntUserMapping;
import com.smxknife.energy.clouds.ents.entmgr.repository.EntUserMappingRepository;
import com.smxknife.energy.datacenter.spi.domain.EntEnergyDwsData;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import com.smxknife.energy.services.metric.spi.domain.Metric;
import com.smxknife.energy.services.metric.spi.service.DatapointService;
import com.smxknife.energy.services.metric.spi.service.MetricService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2021/5/20
 */
@Slf4j
@Component
public class EntMinutesTask implements Runnable {

	@DubboReference(version = "v1")
	private MetricService metricService;

	@DubboReference(version = "v1")
	private DatapointService datapointService;

	@Autowired
	private KafkaDwsProperties kafkaDwsProperties;

	private KafkaProducer<String, EntEnergyDwsData> kafkaProducer;

	@Autowired
	private EntUserMappingRepository entUserMappingRepository;

	@Override
	public void run() {
		log.info("ent minutes task running....");
		LocalDateTime end = LocalDateTime.now();
		LocalDateTime start = end.minusMinutes(5);
		final List<String> entCodes = entUserMappingRepository.findAll().stream().map(EntUserMapping::getEntCode).collect(Collectors.toList());
		entCodes.forEach(entCode -> {
			try {
				log.info("ent {} minutes running", entCode);
				final List<Metric> metrics = metricService.getMetricsByCode(entCode);

				metrics.forEach(metric -> {
					final List<Datapoint> datapoints = datapointService.read(metric.getMetric(), start, end, true);
					final Datapoint startDp = datapoints.get(0);
					final Datapoint endDp = datapoints.get(datapoints.size() - 1);

					final double mValue = endDp.getValue() - startDp.getValue();
					final EntEnergyDwsData dwsData = new EntEnergyDwsData(entCode, "3300", metric + ".minute", mValue, end, "A01", "100", "330100");

					kafkaProducer.send(new ProducerRecord<>(kafkaDwsProperties.getTopic(), dwsData));

				});

			} catch (Exception e) {
				e.printStackTrace();
			}
		});


	}

	@PostConstruct
	public void init() {
		final Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				kafkaDwsProperties.getBootstrapServers());
		props.setProperty(ProducerConfig.ACKS_CONFIG, "-1");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, EntEnergyDwsDataKafkaSerializer.class.getName());

		kafkaProducer = new KafkaProducer<>(props);
	}
}
