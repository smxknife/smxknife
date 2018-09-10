package com.smxknfie.springcloud.netflix.eureka.feign.hystrix.controller;

import com.google.gson.Gson;
import com.smxknfie.springcloud.netflix.eureka.feign.hystrix.client.UserFeignClient;
import com.smxknfie.springcloud.netflix.eureka.feign.hystrix.client.UserFeignClientNoHystrixClient;
import com.smxknfie.springcloud.netflix.eureka.feign.hystrix.client.UserFeignWithHystrixFactoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2018/9/8
 */
@RestController
@RequestMapping("/feign/hystrix")
public class FeignController {

	@Autowired
	UserFeignClient userFeignClient;

	@Autowired
	UserFeignClientNoHystrixClient userFeignClientNoHystrixClient;

	@Autowired
	UserFeignWithHystrixFactoryClient userFeignWithHystrixFactoryClient;

	@RequestMapping("")
	public String index() {
		return userFeignClient.getUser();
	}

	@RequestMapping("{id}")
	public String byId(@PathVariable long id) {
		return userFeignClientNoHystrixClient.getById(id);
	}

	@RequestMapping("post/user")
	public String postUser() {
		Gson gson = new Gson();
		Map<String, Object> user = new HashMap<>();
		user.put("id", 10);
		user.put("name", "hello");
		user.put("age", 90);
		return userFeignWithHystrixFactoryClient.postUser(gson.toJson(user).toString());
	}
}
