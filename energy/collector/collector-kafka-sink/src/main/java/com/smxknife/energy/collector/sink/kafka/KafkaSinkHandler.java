package com.smxknife.energy.collector.sink.kafka;

import com.smxknife.energy.collector.core.driver.sink.SinkHandler;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

/**
 * @author smxknife
 * 2021/5/14
 */
@Slf4j
@Component
public class KafkaSinkHandler implements SinkHandler {

	@Autowired
	private KafkaSinkProperties sinkProperties;

	private KafkaProducer<String, Datapoint> producer;

	@Override
	public void handle(List<Datapoint> datapoints) {
		if (CollectionUtils.isEmpty(datapoints)) {
			log.warn("Metrics is empty");
			return;
		}

		datapoints.forEach(datapoint -> {
			System.out.println(datapoint);
			producer.send(new ProducerRecord<String, Datapoint>(sinkProperties.getTopic(), datapoint.getMetric(), datapoint));
		});
	}

	@PostConstruct
	private void init() {
		final Properties props = new Properties();
		props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				sinkProperties.getBootstrapServers());
		props.setProperty(ProducerConfig.ACKS_CONFIG, "-1");
		props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, DatapointKafkaSerializer.class.getName());

		producer = new KafkaProducer<>(props);

	}
}
