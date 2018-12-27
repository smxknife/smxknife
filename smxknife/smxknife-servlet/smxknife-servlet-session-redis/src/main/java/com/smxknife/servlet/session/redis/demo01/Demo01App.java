package com.smxknife.servlet.session.redis.demo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author smxknife
 * 2018-12-27
 */
@SpringBootApplication
@PropertySource("classpath:/demo01.properties")
public class Demo01App {
	public static void main(String[] args) {
		SpringApplication.run(Demo01App.class, args);
	}
}
