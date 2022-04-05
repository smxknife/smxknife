package com.smxknife.proxy.jdk;

/**
 * @author smxknife
 * 2021/6/21
 */
public class HelloServiceImpl implements HelloService {
	@Override
	public void sayHello(String word) {
		System.out.println("hello, this is impl, welcome | " + word);
	}

	@Override
	public String getWord() {
		return "sayHi";
	}
}
