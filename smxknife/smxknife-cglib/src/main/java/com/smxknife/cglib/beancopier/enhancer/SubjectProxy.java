package com.smxknife.cglib.beancopier.enhancer;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author smxknife
 * 2019/12/27
 */
public class SubjectProxy implements MethodInterceptor {

	public Object getInstance(Class<?> cls) {
		Enhancer enhancer = new Enhancer();
		// 设置即将生成类的父类
		enhancer.setSuperclass(cls);
		enhancer.setCallback(this);

		return enhancer.create();
	}

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		// 业务增强
		before();
		Object obj = methodProxy.invokeSuper(o, objects);
		after();
		return obj;
	}

	private void after() {
		System.out.println("after");
	}

	private void before() {
		System.out.println("before");
	}


}
