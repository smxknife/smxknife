package com.smxknfie.springcloud.netflix.eureka.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author smxknife
 * 2018/9/8
 */
@RestController
@RequestMapping("/hystrix")
@Log
public class IndexController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping
	@HystrixCommand(fallbackMethod = "getUserFallback")
	public String getUser() {
		return restTemplate.getForObject("http://user-service/user", String.class);
	}

	// 注意参数要保持一致
	public String getUserFallback() {
		log.info(">> getUserFallback");
		return "fallback user";
	}
}
