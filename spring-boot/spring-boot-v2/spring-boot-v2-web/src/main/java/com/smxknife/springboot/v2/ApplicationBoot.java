package com.smxknife.springboot.v2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationBoot {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApplicationBoot.class);
		app.setBanner(new AppBanner());
//		app.setBannerMode(Banner.Mode.LOG);
		app.run(args);
	}
}
