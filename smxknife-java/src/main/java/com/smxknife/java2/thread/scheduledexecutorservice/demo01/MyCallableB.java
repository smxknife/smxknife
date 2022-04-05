package com.smxknife.java2.thread.scheduledexecutorservice.demo01;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/29
 */
public class MyCallableB implements Callable<String> {
	@Override
	public String call() throws Exception {
		System.out.println("B begin: " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
		System.out.println("B end: " + Thread.currentThread().getName() + " " + System.currentTimeMillis());
		return "returnB";
	}
}
