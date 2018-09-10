package com.smxknife.springcloud.netflix.eureka.user.controller;

import com.smxknife.springcloud.netflix.eureka.user.domain.User;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@Log
public class UserController {

	@RequestMapping("")
	public List<User> userList() {
		log.info("access user list");
		List<User> users = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
//			log.info("user name: user" + i );
			User user = new User();
			user.setId(i + 1);
			user.setName("user" + i);
			user.setAge(i);
			users.add(user);
		}
		return users;
	}

	@RequestMapping("{id}")
	public User getById(@PathVariable long id) {
		User user = new User();
		user.setId(id);
		user.setName("user" + id);
		user.setAge(1);
		return user;
	}

	@PostMapping(value = "post", produces = "application/json")
	public String post(@RequestBody String user) {
		return user.toString();
	}
}
