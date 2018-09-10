package com.smxknfie.springcloud.netflix.eureka.feign.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author smxknife
 * 2018/9/8
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class EurekaFeignHystrixApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaFeignHystrixApp.class, args);
	}
}
