package com.smxknife.energy.services.alarm.infras.mq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/5/17
 */
@Data
@Component
@ConfigurationProperties(prefix = "alarm.rule.producer")
public class AlarmProducerProperties {

	private String topic;
	private String bootstrapServers;

}
