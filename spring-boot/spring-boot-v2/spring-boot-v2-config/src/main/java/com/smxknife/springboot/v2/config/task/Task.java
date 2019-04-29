package com.smxknife.springboot.v2.config.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Task {

    @Scheduled(cron = "*/35 * * * * ?")
    public void test1() {
        System.out.println("test1" + LocalDateTime.now());
    }

    @Scheduled(cron = "0/35 * * * * ?")
    public void test2() {
        System.out.println("test2" + LocalDateTime.now());
    }
}
