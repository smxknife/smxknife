package com.smxknfie.springcloud.netflix.eureka.feign.config;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author smxknife
 * 2018/9/9
 */
@Configuration
public class UserFeignClientNoHystrixClientConfiguration {

	@Bean
	@Scope("prototype")
	public Feign.Builder feignBuilder() {
		return Feign.builder();
	}
}
