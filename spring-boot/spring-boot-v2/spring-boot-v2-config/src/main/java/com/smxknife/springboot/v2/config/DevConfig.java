package com.smxknife.springboot.v2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;

@Configuration
@Profile("dev")
public class DevConfig {

	@PostConstruct
	public void after() {
		System.out.println("dev-config");
	}
}
