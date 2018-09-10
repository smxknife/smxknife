package com.smxknfie.springcloud.netflix.eureka.hystrix.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author smxknife
 * 2018/9/9
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableTurbine
public class EurekaHystrixTurbineApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaHystrixTurbineApp.class, args);
	}
}
