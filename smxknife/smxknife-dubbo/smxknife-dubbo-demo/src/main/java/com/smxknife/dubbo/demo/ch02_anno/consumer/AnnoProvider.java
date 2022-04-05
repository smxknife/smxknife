package com.smxknife.dubbo.demo.ch02_anno.consumer;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConsumerConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author smxknife
 * 2020/11/7
 */
public class AnnoProvider {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
		context.start();

		EchoConsumer echoConsumer = context.getBean(EchoConsumer.class);
		echoConsumer.echo("enen");
	}

	@Configuration
	@EnableDubbo(scanBasePackages = "com.smxknife.dubbo.demo.ch02_anno.consumer")
	@ComponentScan(value = "com.smxknife.dubbo.demo.ch02_anno.consumer")
	static class ConsumerConfiguration {

		@Bean
		public ApplicationConfig applicationConfig() {
			ApplicationConfig applicationConfig = new ApplicationConfig();
			applicationConfig.setName("ehco-anno-consumer");
			return applicationConfig;
		}

		@Bean
		public ConsumerConfig consumerConfig() {
			return new ConsumerConfig();
		}

		@Bean
		public RegistryConfig registryConfig() {
			RegistryConfig registryConfig = new RegistryConfig();
			registryConfig.setAddress("zookeeper://localhost:2181");
			registryConfig.setClient("curator");
			return registryConfig;
		}
	}
}
