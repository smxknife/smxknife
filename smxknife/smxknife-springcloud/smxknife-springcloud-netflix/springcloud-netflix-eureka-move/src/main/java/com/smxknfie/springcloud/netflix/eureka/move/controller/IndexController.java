package com.smxknfie.springcloud.netflix.eureka.move.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author smxknife
 * 2018/9/5
 */
@RestController
@RequestMapping("/move")
public class IndexController {

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/user")
	public Object getUser() {
		return restTemplate.getForObject("http://user-service/user", String.class);
	}
}
