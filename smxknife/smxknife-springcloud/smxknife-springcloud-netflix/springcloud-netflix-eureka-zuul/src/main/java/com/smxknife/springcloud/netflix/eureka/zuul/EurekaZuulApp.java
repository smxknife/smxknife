package com.smxknife.springcloud.netflix.eureka.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

/**
 * @author smxknife
 * 2018/9/16
 */
@SpringBootApplication
@EnableZuulProxy
public class EurekaZuulApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaZuulApp.class, args);
	}

	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
		return new PatternServiceRouteMapper(
				"(?<name>^.+)-(?<version>v.+$)",
				"${version}/${name}");
	}
}
