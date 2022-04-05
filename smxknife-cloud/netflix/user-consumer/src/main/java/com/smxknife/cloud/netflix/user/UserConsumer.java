package com.smxknife.cloud.netflix.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author smxknife
 * 2021/4/30
 */
@SpringBootApplication
@EnableFeignClients
public class UserConsumer {

	public static void main(String[] args) {
		SpringApplication.run(UserConsumer.class, args);
	}
}
