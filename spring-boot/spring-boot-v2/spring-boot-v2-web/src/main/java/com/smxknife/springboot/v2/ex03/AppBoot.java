package com.smxknife.springboot.v2.ex03;

import com.smxknife.springboot.v2.ex03.listener.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AppBoot {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder();
        builder.listeners(new MyApplicationStartingListener());
        builder.listeners(new MyApplicationEnvironmentPreparedListener());
        builder.listeners(new MyApplicationPreparedListener());
        builder.listeners(new MyApplicationStartedListener());
//        builder.listeners(new MyApplicationReadyListener());
        builder.sources(AppBoot.class);
        builder.run(args);
    }
}
