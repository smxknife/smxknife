package com.smxknife.springboot._10_retry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author smxknife
 * 2021/8/1
 */
@EnableRetry
@SpringBootApplication
public class RetryMain {
	public static void main(String[] args) {
		SpringApplication.run(RetryMain.class, args);
	}
}
