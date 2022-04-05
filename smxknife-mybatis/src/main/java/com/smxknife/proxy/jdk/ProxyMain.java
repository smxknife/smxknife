package com.smxknife.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author smxknife
 * 2021/6/21
 */
public class ProxyMain {
	public static void main(String[] args) {
		HelloService helloService = new HelloServiceImpl();
		final HelloServiceInvocationHandler invocationHandler = new HelloServiceInvocationHandler(helloService);
		final HelloService proxyService = (HelloService)Proxy.newProxyInstance(HelloServiceInvocationHandler.class.getClassLoader(), new Class[]{HelloService.class}, invocationHandler);

		proxyService.sayHello("你好");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
		System.out.println(proxyService.getWord());
	}
}
