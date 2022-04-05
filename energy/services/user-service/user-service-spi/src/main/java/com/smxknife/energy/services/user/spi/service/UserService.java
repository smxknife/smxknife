package com.smxknife.energy.services.user.spi.service;

import com.smxknife.energy.services.user.spi.domain.User;

import java.util.List;

/**
 * @author smxknife
 * 2021/5/19
 */
public interface UserService {

	void create(User user);

	List<User> getAll();

	List<User> getByAccountIn(List<String> accounts);

}
