package com.smxknife.cloud.netflix;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author smxknife
 * 2021/5/29
 */
@SpringBootApplication
@EnableDistributedTransaction
public class LcnTxOrderBoot {
	public static void main(String[] args) {
		SpringApplication.run(LcnTxOrderBoot.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
