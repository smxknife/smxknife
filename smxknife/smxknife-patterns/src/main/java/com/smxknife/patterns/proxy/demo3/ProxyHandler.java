package com.smxknife.patterns.proxy.demo3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author smxknife
 * 2019/12/27
 */
public class ProxyHandler implements InvocationHandler {

	private Object target;

	public ProxyHandler(Object target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		preHandle();
		Object invoke = method.invoke(target, args);
		postHandle();
		return invoke;
	}

	private void postHandle() {

	}

	private void preHandle() {

	}
}
