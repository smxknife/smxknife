package com.smxknfie.softmarket.accesstoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class AccessTokenBoot {

	public static void main(String[] args) {
		SpringApplication.run(AccessTokenBoot.class, args);
	}

}
