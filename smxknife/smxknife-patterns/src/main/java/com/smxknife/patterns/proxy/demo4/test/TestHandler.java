package com.smxknife.patterns.proxy.demo4.test;

import com.smxknife.patterns.proxy.demo4.JdkClassLoader;
import com.smxknife.patterns.proxy.demo4.JdkInvocationHandler;
import com.smxknife.patterns.proxy.demo4.JdkProxy;

import java.lang.reflect.Method;

/**
 * @author smxknife
 * 2019/12/27
 */
public class TestHandler implements JdkInvocationHandler {

	private Object target;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("preDo");
		Object invoke = method.invoke(target, args);
		System.out.println("postDo");
		return invoke;
	}

	public Object getInstance(Object target) {
		this.target = target;

		Class<?> targetClass = target.getClass();

		return JdkProxy.newProxyInstance(new JdkClassLoader(), targetClass.getInterfaces(), this);
	}
}
