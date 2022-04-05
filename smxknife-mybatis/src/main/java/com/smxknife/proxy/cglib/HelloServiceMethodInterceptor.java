package com.smxknife.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author smxknife
 * 2021/6/22
 */
public class HelloServiceMethodInterceptor implements MethodInterceptor {

	public <T> T getInstance(Class<T> clazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);

		return (T)enhancer.create();
	}

	@Override
	public Object intercept(Object o, Method method, Object[] params, MethodProxy methodProxy) throws Throwable {
		before();
		final Object o1 = methodProxy.invokeSuper(o, params);
		after();
		return o1;
	}

	private void after() {
		System.out.println("after ...");
	}

	private void before() {
		System.out.println("before ....");
	}
}
