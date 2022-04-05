package com.smxknife.springboot._06_jsonview;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Setter;

/**
 * @author smxknife
 * 2021/8/1
 */
@Setter
public class User {

	private Integer age;
	private String username;
	private String password;

	public User() {}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.age = 10;
	}

	public interface UserDetail {}

	public interface UserInfo extends UserDetail {}

	@JsonView(UserInfo.class)
	public String getUsername() {
		return this.username;
	}

	@JsonView(UserDetail.class)
	public String getPassword() {
		return this.password;
	}

	@JsonView(UserInfo.class)
	public Integer getAge() {
		return age;
	}
}
