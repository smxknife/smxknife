package com.smxknife.cloud.netflix.user.web.controller;

import com.smxknife.cloud.netflix.user.User;
import com.smxknife.cloud.netflix.user.UserApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/4/30
 */
@RestController
@RequestMapping("/user")
public class UserController implements UserApi {

	@GetMapping("alive")
	public String isAlive() {
		return "ok";
	}

	@Override
	public String getName() {
		return "mingyi";
	}

	@Override
	public User getById(@RequestBody Long id) {
		return new User(id, "hello" + id);
	}
}
