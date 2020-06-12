package com.smxknife.dubbo.api.service;

import com.smxknife.dubbo.api.model.User;

/**
 * @author smxknife
 * 2019/12/9
 */
public interface UserService {

	User getUser(String name);
}
