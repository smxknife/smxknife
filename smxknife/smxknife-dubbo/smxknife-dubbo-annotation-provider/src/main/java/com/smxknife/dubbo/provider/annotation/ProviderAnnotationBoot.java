package com.smxknife.dubbo.provider.annotation;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author smxknife
 * 2019/12/11
 */
public class ProviderAnnotationBoot {
	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
		context.start();
		System.in.read();
	}

	@Configuration
	@EnableDubbo(scanBasePackages = "com.smxknife.dubbo.provider.annotation.service.impl")
	static class ProviderConfiguration {

		@Bean
		public ProviderConfig providerConfig() {
			return new ProviderConfig();
		}

		@Bean
		public ApplicationConfig applicationConfig() {
			ApplicationConfig config = new ApplicationConfig();
			config.setName("echo-annotation-provider");
			return config;
		}

		@Bean
		public RegistryConfig registryConfig() {
			RegistryConfig registryConfig = new RegistryConfig();
//			registryConfig.setProtocol("zookeeper");
			registryConfig.setAddress("zookeeper://127.0.0.1:2181");
			registryConfig.setClient("curator");
//			registryConfig.setPort(2181);
			return registryConfig;
		}

		@Bean
		public ProtocolConfig protocolConfig() {
			ProtocolConfig protocolConfig = new ProtocolConfig();
			protocolConfig.setName("dubbo");
			protocolConfig.setPort(20880);
			return protocolConfig;
		}
	}
}
