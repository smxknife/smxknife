package com.smxknfie.springcloud.netflix.eureka.move;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 模拟ribbon
 * @author smxknife
 * 2018/9/5
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaMoveApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaMoveApp.class, args);
	}
}
