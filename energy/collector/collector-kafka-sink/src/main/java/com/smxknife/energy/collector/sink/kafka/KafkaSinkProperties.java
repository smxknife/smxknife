package com.smxknife.energy.collector.sink.kafka;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/5/14
 */
@Data
@Component
@ConfigurationProperties("sink.kafka")
public class KafkaSinkProperties {

	private String topic;
	private String bootstrapServers;

}
