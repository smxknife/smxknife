package com.smxknfie.springcloud.netflix.eureka.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author smxknife
 * 2018/9/7
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EurekaFeignApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaFeignApp.class, args);
	}
}
