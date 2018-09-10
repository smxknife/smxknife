package com.smxknfie.springcloud.netflix.eureka.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;

/**
 * @author smxknife
 * 2018/9/5
 */
//@Configuration
//@ExcludeAnnotation
public class UserRibbonConfig2 {

	@Bean
	public IRule ribbonRule(IClientConfig iClientConfig) {
		return new RoundRobinRule();
	}
}
