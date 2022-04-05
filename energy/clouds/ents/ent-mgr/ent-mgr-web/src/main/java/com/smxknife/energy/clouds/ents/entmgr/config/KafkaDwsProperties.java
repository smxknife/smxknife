package com.smxknife.energy.clouds.ents.entmgr.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/5/20
 */
@Data
@Component
@ConfigurationProperties(prefix = "ent.kafka.dws")
public class KafkaDwsProperties {

	private String topic;
	private String bootstrapServers;
}
