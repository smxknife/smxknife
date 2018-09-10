package com.smxknfie.springcloud.netflix.eureka.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author smxknife
 * 2018/9/5
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaCustomRibbonApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaCustomRibbonApp.class, args);
	}
}
