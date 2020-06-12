package com.smxknife.spring.manual.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author smxknife
 * 2019/12/30
 */
@Getter
@Setter
public class User {
	private String name;
	private int age;

	public User() {}

	public User(String name, int age) {
		this.age = age;
		this.name = name;
	}
}
