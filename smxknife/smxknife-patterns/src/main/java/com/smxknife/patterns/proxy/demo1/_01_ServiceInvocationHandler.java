package com.smxknife.patterns.proxy.demo1;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author smxknife
 * 2019/10/29
 */
@AllArgsConstructor
public class _01_ServiceInvocationHandler implements InvocationHandler {

	private Object object;

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		proDo();
		method.invoke(object, args);
		postDo();
		return null;
	}

	private void postDo() {
		System.out.println(object.getClass().getSimpleName() + " | postDo");
	}

	private void proDo() {
		System.out.println(object.getClass().getSimpleName() + " | preDo");
	}
}
