package com.smxknife.java2.thread.completionservice.demo02;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/27
 */
public class MyCallableB implements Callable<String> {
	@Override
	public String call() throws Exception {
		System.out.println("MyCallableB begin " + System.currentTimeMillis());
		TimeUnit.SECONDS.sleep(5);
		if (1 == 1) {
			throw new Exception("我故意的...");
		}
		System.out.println("MyCallableB end " + System.currentTimeMillis());
		return "returnB";
	}
}
