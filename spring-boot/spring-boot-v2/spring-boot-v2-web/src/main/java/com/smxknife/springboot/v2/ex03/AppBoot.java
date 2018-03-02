package com.smxknife.springboot.v2.ex03;

import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class AppBoot {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder();
//        builder.listeners()
        builder.sources(AppBoot.class);
        builder.run(args);
    }
}
