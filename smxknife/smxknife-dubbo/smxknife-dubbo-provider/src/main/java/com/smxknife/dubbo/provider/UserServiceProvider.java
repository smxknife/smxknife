package com.smxknife.dubbo.provider;

import com.smxknife.dubbo.api.model.User;
import com.smxknife.dubbo.api.service.UserService;

/**
 * @author smxknife
 * 2019/12/9
 */
public class UserServiceProvider implements UserService {
	@Override
	public User getUser(String name) {
		return User.newInstance(name);
	}
}
