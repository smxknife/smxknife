package com.smxknife.proxy.cglib;

/**
 * @author smxknife
 * 2021/6/22
 */
public class HelloService {
	public void sayHello(String word) {
		System.out.println("hello, this is impl, welcome | " + word);
	}

	public String getWord() {
		return "sayHi";
	}
}
