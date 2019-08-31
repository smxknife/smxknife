package com.smxknife.java2.thread.scheduledexecutorservice.demo01;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/29
 */
public class MyCallableA implements Callable<String> {
	@Override
	public String call() throws Exception {
		System.out.println("A begin: " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

		TimeUnit.SECONDS.sleep(3);

		System.out.println("A end: " + Thread.currentThread().getName() + " " + System.currentTimeMillis());

		return "returnA";
	}
}
