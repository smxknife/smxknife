package com.smxknife.java2.thread.designpattern._03GuardedSuspension.demo;

import lombok.Getter;
import lombok.ToString;

/**
 * 该类表示请求
 * @author smxknife
 * 2019/9/24
 */
@ToString
public class Request {
	@Getter
	private final String name;

	public Request(String name) {
		this.name = name;
	}
}
