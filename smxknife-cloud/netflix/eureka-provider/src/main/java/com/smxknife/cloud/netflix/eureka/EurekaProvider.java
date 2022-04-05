package com.smxknife.cloud.netflix.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author smxknife
 * 2021/4/29
 */
// 在老版本里面需要加EnableDiscoveryClient或者EnableEurekaClient注解，在新版本中可以不需要加
//@EnableDiscoveryClient
@SpringBootApplication
public class EurekaProvider {
	public static void main(String[] args) {
		SpringApplication.run(EurekaProvider.class, args);
	}
}
