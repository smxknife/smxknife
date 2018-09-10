package com.smxknfie.springcloud.netflix.eureka.feign.controller;

import com.google.gson.Gson;
import com.smxknfie.springcloud.netflix.eureka.feign.inter.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2018/9/7
 */
@RestController
@RequestMapping("/feign")
public class FeignController {

	@Autowired
	UserFeignClient userFeignClient;

	@RequestMapping("")
	public String index() {
		return userFeignClient.getUser();
	}

	@RequestMapping("{id}")
	public String byId(@PathVariable long id) {
		return userFeignClient.getById(id);
	}

	@RequestMapping("post/user")
	public String postUser() {
		Gson gson = new Gson();
		Map<String, Object> user = new HashMap<>();
		user.put("id", 10);
		user.put("name", "hello");
		user.put("age", 90);
		return userFeignClient.postUser(gson.toJson(user).toString());
	}
}
