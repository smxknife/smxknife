package com.smxknfie.springcloud.netflix.eureka.hystrix.dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @author smxknife
 * 2018/9/9
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class EurekaHystrixDashboardApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaHystrixDashboardApp.class, args);
	}
}
