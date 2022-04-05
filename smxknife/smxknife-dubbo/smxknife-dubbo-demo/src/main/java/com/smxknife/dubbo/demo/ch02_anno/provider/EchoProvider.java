package com.smxknife.dubbo.demo.ch02_anno.provider;

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
 * 2020/11/7
 */
public class EchoProvider {

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
		context.start();

		System.in.read();
	}

	@Configuration
	@EnableDubbo(scanBasePackages = "com.smxknife.dubbo.demo.ch02_anno.provider")
	static class ProviderConfiguration {
		@Bean
		public ProviderConfig providerConfig() {
			return new ProviderConfig();
		}

		@Bean
		public ApplicationConfig applicationConfig() {
			ApplicationConfig applicationConfig = new ApplicationConfig();
			applicationConfig.setName("echo-anno-provider");
			return applicationConfig;
		}

		@Bean
		public RegistryConfig registryConfig() {
			RegistryConfig registryConfig = new RegistryConfig();
			registryConfig.setAddress("zookeeper://127.0.0.1:2181");
			registryConfig.setClient("curator");
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
