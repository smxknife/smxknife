package com.smxknife.patterns.proxy.demo4;

import java.lang.reflect.Method;

/**
 * @author smxknife
 * 2019/12/27
 */
public interface JdkInvocationHandler {
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
