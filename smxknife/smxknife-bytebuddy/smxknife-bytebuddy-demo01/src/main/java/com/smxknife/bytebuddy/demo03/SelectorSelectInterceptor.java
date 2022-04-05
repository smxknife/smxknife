package com.smxknife.bytebuddy.demo03;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.concurrent.Callable;

/**
 * @author smxknife
 * 2020/11/2
 */
public class SelectorSelectInterceptor {

	@RuntimeType
	public static Object intercept(@SuperCall Callable<?> callable) throws Exception {
		System.out.println("Selector Thread = " + Thread.currentThread().getName());
		return callable.call();
	}
}
