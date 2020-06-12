package com.smxknife.dubbo.consumer.annotation.config;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author smxknife
 * 2019/12/11
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.smxknife.dubbo")
@ComponentScan(value = {"com.smxknife.dubbo"})
public class ConsumerConfiguration {

	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("echo-annotation-consumer");
		return applicationConfig;
	}

	@Bean
	public ConsumerConfig consumerConfig() {
		return new ConsumerConfig();
	}

	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		// TODO 这里这种方式是无法注册成功的，会导致一直向127.0.0.1:9090注册，目前原因还未查明
//		registryConfig.setProtocol("zookeeper");
		registryConfig.setAddress("zookeeper://localhost:2181");
		registryConfig.setClient("curator");
//		registryConfig.setPort(2181);
		return registryConfig;
	}
}
