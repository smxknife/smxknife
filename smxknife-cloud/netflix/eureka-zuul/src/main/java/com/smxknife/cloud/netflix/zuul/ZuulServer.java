package com.smxknife.cloud.netflix.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author smxknife
 * 2021/5/4
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulServer {
	public static void main(String[] args) {
		SpringApplication.run(ZuulServer.class, args);
	}
}
