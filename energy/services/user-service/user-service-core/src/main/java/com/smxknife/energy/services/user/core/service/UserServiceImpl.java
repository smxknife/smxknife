package com.smxknife.energy.services.user.core.service;

import com.smxknife.energy.services.user.core.dao.UserDao;
import com.smxknife.energy.services.user.spi.domain.User;
import com.smxknife.energy.services.user.spi.service.UserService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
@DubboService(version = "v1", interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public void create(User user) {
		userDao.create(user);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public List<User> getByAccountIn(List<String> accounts) {
		return userDao.getByAccountIn(accounts);
	}
}
