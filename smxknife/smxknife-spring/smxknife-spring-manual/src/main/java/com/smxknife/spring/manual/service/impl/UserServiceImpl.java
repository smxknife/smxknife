package com.smxknife.spring.manual.service.impl;

import com.google.gson.Gson;
import com.smxknife.spring.manual.annotation.Service;
import com.smxknife.spring.manual.model.User;
import com.smxknife.spring.manual.service.UserService;

/**
 * @author smxknife
 * 2019/12/30
 */
@Service
public class UserServiceImpl implements UserService {
	@Override
	public String getUser(String name) {
		Gson gson = new Gson();
		return gson.toJson(new User(name, 11));
	}
}
