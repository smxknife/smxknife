package com.smxknife.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 这个invocationHandler里需要实现其他业务
 * @author smxknife
 * 2021/6/21
 */
public class HelloServiceInvocationHandler implements InvocationHandler {

	private Object beProxyObject;

	public HelloServiceInvocationHandler(HelloService service) {
		this.beProxyObject = service;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("proxy = " + proxy.getClass());
		System.out.println("method = " + method);
		if (Objects.nonNull(args)) {
			Arrays.stream(args).forEach(System.out::println);
		}

		System.out.println("-------------- doPre -----------------");
		final Object invoke = method.invoke(beProxyObject, args);
		System.out.println("-------------- doPost -----------------");

		return invoke;
	}
}
