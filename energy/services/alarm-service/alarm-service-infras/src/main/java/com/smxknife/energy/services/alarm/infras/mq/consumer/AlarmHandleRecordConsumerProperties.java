package com.smxknife.energy.services.alarm.infras.mq.consumer;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/5/17
 */
@Data
@Component
@ConfigurationProperties(prefix = "alarm.handle.record.consumer")
public class AlarmHandleRecordConsumerProperties {

	private String topic;
	private String group;
	private String bootstrapServers;
}
