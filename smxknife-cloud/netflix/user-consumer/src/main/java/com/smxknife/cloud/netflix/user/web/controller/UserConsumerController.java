package com.smxknife.cloud.netflix.user.web.controller;

import com.smxknife.cloud.netflix.user.User;
import com.smxknife.cloud.netflix.user.service.FeignHystrixUserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/4/30
 */
@RestController
@RequestMapping("/user")
public class UserConsumerController {

//	@Autowired
//	private UserConsumerApi useApi;

//	@Autowired
//	private UserExtApi useApi;

	@Autowired
	private FeignHystrixUserApi useApi;

//	@GetMapping("/call/alive")
//	public String callAlive() {
//		return useApi.isAlive();
//	}

	@GetMapping("/call/name")
	public String callName() {
		return useApi.getName();
	}

	@GetMapping("call/id")
	public User callId(Long id) {
		return useApi.getById(id);
	}
}
