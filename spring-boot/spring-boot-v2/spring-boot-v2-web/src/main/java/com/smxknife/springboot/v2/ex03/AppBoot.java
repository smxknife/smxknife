package com.smxknife.springboot.v2.ex03;

import com.smxknife.springboot.v2.ex03.listener.MyApplicationEnvironmentPreparedListener;
import com.smxknife.springboot.v2.ex03.listener.MyApplicationPreparedListener;
import com.smxknife.springboot.v2.ex03.listener.MyApplicationStartedListener;
import com.smxknife.springboot.v2.ex03.listener.MyApplicationStartingListener;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AppBoot {

    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 42;
    }

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
