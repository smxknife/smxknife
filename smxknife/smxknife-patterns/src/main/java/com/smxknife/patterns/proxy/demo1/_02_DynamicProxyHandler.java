package com.smxknife.patterns.proxy.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author smxknife
 * 2019/12/27
 */
public class _02_DynamicProxyHandler implements InvocationHandler {

	private Object target;

	public _02_DynamicProxyHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		before();
		Object invoke = method.invoke(this.target, args);
		System.out.println("====" + invoke);
		after();
		return invoke;
	}

	private void after() {
		System.out.println("dynamic service run after");
	}

	private void before() {
		System.out.println("dynamic service run before");
	}
}
