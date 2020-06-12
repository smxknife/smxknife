package com.smxknife.dubbo.api.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author smxknife
 * 2019/12/9
 */
@Getter
@Setter
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String userName;
	private String password;

	public static User newInstance(String userName) {
		User user = new User();
		user.id = 1;
		user.password = "123456";
		user.userName = userName;
		return user;
	}

}
