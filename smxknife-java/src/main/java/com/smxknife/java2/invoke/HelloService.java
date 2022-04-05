package com.smxknife.java2.invoke;

/**
 * @author smxknife
 * 2021/6/28
 */
public class HelloService {
	public String sayHi(String someone, int num) {
		return "hello, " + someone + "-" + num;
	}

	private String sayHello() {
		return "hello";
	}
}
