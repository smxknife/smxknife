package com.smxknife.springboot.v2.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AppBoot {
	public static void main(String[] args) {
		SpringApplication.run(AppBoot.class, args);
	}
}
