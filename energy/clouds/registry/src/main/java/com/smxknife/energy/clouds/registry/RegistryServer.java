package com.smxknife.energy.clouds.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author smxknife
 * 2021/5/19
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistryServer {
	public static void main(String[] args) {
		SpringApplication.run(RegistryServer.class, args);
	}
}
