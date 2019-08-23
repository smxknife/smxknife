package com.smxknife.java2.thread.futureAndCallable.callableGet;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019-08-19
 */
public class CallableGetMethod implements Callable<String> {

	private int age;

	public CallableGetMethod(int age) {
		this.age = age;
	}

	@Override
	public String call() throws Exception {
		TimeUnit.SECONDS.sleep(2);
		return String.format("年龄是：%s", age);
	}
}
