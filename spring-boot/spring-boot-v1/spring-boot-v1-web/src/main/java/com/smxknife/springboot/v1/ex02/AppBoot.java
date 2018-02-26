package com.smxknife.springboot.v1.ex02;

import com.smxknife.springboot.v1.ex02.children.child1.Child1Config;
import com.smxknife.springboot.v1.ex02.children.child2.Child2Config;
import com.smxknife.springboot.v1.ex02.parent.ParentConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.PropertySource;

public class AppBoot {
    public static void main(String[] args) {
        System.setProperty("spring.devtools.restart.enabled", "false");
        new SpringApplicationBuilder()
                .sources(ParentConfig.class)
                .child(Child1Config.class)
                .sibling(Child2Config.class)
                .banner(new AppBanner())
                .logStartupInfo(false)
                .run(args);
    }
}
