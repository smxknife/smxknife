package com.smxknife.springcloud.netflix.eureka.server.ha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author smxknife
 * 2018/9/7
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerHaApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerHaApp.class, args);
	}
}
