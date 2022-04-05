package com.smxknife.energy.datacenter.core.consumer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/5/20
 */
@Data
@Component
@ConfigurationProperties(prefix = "datacenter.dws.consumer")
public class DwsConsumerProperties {

	private String topic;
	private String group;
	private String bootstrapServers;
}
