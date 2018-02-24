package com.smxknife.springboot.v2;

import com.smxknife.springboot.v2.config.AppConfig;
import org.springframework.boot.SpringApplication;

import java.util.Set;

public class AppBoot1 {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AppConfig.class);
        app.setBanner(new AppBanner());
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

        app.run(args);
    }
}
