package com.smxknfie.springcloud.netflix.eureka.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author smxknife
 * 2018/9/5
 */
@Configuration
public class UserRibbonConfig {

	@Bean
	public IRule ribbonRule(IClientConfig iClientConfig) {
		return new RandomRule();
	}
}
