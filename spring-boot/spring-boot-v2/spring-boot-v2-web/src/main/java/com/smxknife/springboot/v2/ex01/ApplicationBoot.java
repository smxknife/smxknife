package com.smxknife.springboot.v2.ex01;

import com.smxknife.springboot.v2.AppBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:parent.properties")
public class ApplicationBoot {
	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication app = new SpringApplication(ApplicationBoot.class);
		app.setBanner(new AppBanner());
//		app.setBannerMode(Banner.Mode.LOG);
		app.getSources().forEach(it -> {
			System.out.print("source >> ");
			System.out.println(it);
		});
		app.getAllSources().forEach(it -> {
			System.out.print("all sources >> ");
			System.out.println(it);
		});
		app.getInitializers().forEach(it -> {
			System.out.print("initializers >> ");
			System.out.println(it);
		});
		app.getListeners().forEach(it -> {
			System.out.print("listeners >> ");
			System.out.println(it);
		});

		System.out.println("-----------------");
		System.out.println(app.getClassLoader());
		System.out.println(app.getMainApplicationClass());
		System.out.println(app.getResourceLoader());
		System.out.println(app.getWebApplicationType());
		System.out.println("-----------------");

		app.setLogStartupInfo(true);

		app.run(args);
	}
}
