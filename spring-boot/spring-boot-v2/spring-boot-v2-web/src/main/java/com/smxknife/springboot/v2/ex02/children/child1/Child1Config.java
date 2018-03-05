package com.smxknife.springboot.v2.ex02.children.child1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;

@SpringBootApplication
@PropertySource("classpath:/child1.properties")
public class Child1Config {

	@Value("${app.name}")
	private String appName;

	@PostConstruct
	public void after() {
		System.out.println("---- " + appName);
	}
}
