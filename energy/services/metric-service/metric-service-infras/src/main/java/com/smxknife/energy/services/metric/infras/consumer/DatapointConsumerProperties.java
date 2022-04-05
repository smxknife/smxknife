package com.smxknife.energy.services.metric.infras.consumer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/5/17
 */
@Data
@Component
@ConfigurationProperties(prefix = "datapoint.consumer")
public class DatapointConsumerProperties {

	private String topic;
	private String group;
	private String bootstrapServers;
}
