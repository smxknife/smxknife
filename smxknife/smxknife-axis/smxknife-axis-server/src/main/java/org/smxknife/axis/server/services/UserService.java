package org.smxknife.axis.server.services;

/**
 * @author smxknife
 * 2019/10/18
 */
public interface UserService {

	String userInfo(String name);

	String userList();

	String self();
}
