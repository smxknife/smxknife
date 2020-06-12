package com.smxknife.spring.schema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @author smxknife
 * 2019/12/14
 */
@SpringBootApplication
@ImportResource(locations = "smxknife.xml")
public class AppBoot {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(AppBoot.class, args);
		ServiceBean serviceBean = applicationContext.getBean(ServiceBean.class);
		System.out.println(serviceBean.getName());
		ApplicationConfig applicationConfig = applicationContext.getBean(ApplicationConfig.class);
		System.out.println(applicationConfig.getName());
		System.out.println(applicationConfig.getVersion());
		System.out.println(applicationConfig.getDescription());
	}
}
