package com.smxknife.cloud.netflix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

/**
 * @author smxknife
 * 2021/4/29
 */
@SpringBootApplication
public class EurekaConsumer {
	public static void main(String[] args) {
		SpringApplication.run(EurekaConsumer.class, args);
	}

	@Bean
	@Primary
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean("loadBalancer")
	@LoadBalanced
	public RestTemplate loadBalancerRestTemplate() {
		return new RestTemplate();
	}


}
