package com.smxknife.graphql.ex2.service;

import com.smxknife.graphql.ex2.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	public User findById(int id) {
		User user = new User();
		user.setId(id);
		user.setAccount("admin");
		user.setPassword("123456");
		return user;
	}
}
