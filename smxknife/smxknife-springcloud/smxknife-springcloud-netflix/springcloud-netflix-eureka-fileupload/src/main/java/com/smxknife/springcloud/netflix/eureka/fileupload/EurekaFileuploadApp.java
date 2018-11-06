package com.smxknife.springcloud.netflix.eureka.fileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author smxknife
 * 2018/9/16
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaFileuploadApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaFileuploadApp.class, args);
	}
}
