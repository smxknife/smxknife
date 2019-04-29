package com.smxknife.servlet.springboot.demo02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author smxknife
 * 2018-12-27
 */
@SpringBootApplication
public class Demo02App {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Demo02App.class, args);
	}
}
