package com.smxknfie.springcloud.netflix.eureka.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author smxknife
 * 2018/9/8
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class EurekaHystrixApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaHystrixApp.class, args);
	}
}
