package com.smxknife.ehcache.cache.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author smxknife
 * 2020/6/11
 */
@EnableCaching
@SpringBootApplication
public class AppBoot {
	public static void main(String[] args) {
		SpringApplication.run(AppBoot.class, args);
	}
}
