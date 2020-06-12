package com.smxknife.servlet.springboot.demo04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author smxknife
 * 2020/2/10
 */
@SpringBootApplication
@Import(Demo4AnnoImportRegistrator.class)
public class Demo04App {
	public static void main(String[] args) {
		SpringApplication.run(Demo04App.class, args);
	}

	@Bean(initMethod = "init", destroyMethod = "des")
	public BeanInitAndDestroy beanInitAndDestroy() {
		return new BeanInitAndDestroy();
	}
}
