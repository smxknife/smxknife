package com.smxknife.springboot._07_jsonserializable;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/8/1
 */
@RestController
@RequestMapping("/07/user")
public class UserController {

	@GetMapping
	public User getUser() {
		final User user = new User();
		user.setUsername("1111");
		user.setPassword("222");
		user.setStatus(1);
		return user;
	}
}
