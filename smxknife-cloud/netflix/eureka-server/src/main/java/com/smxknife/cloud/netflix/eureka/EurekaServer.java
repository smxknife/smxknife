package com.smxknife.cloud.netflix.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author smxknife
 * 2021/4/28
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServer.class, args);
	}
}
