package com.smxknife.springboot.v2;

import com.smxknife.springboot.v2.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

public class AppBoot2 {
    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(AppConfig.class)
                .banner(new AppBanner())
                .run(args);
    }
}
