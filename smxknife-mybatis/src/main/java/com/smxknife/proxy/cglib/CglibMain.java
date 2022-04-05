package com.smxknife.proxy.cglib;

/**
 * @author smxknife
 * 2021/6/22
 */
public class CglibMain {
	public static void main(String[] args) {
		final HelloService helloService = new HelloServiceMethodInterceptor().getInstance(HelloService.class);
		helloService.sayHello("你好");
		System.out.println(helloService.getWord());
	}
}
