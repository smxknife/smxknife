package com.smxknife.java2.thread.completionservice.demo02;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/27
 */
public class MyCallableA implements Callable<String> {
	@Override
	public String call() throws Exception {
		System.out.println("MyCallableA begin " + System.currentTimeMillis());
		TimeUnit.SECONDS.sleep(1);
		System.out.println("MyCallableA end " + System.currentTimeMillis());
		return "returnA";
	}
}
