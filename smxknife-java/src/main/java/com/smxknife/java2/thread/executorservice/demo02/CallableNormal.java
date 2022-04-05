package com.smxknife.java2.thread.executorservice.demo02;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/29
 */
public class CallableNormal implements Callable<String> {
	@Override
	public String call() throws Exception {
		System.out.println("Normal " + Thread.currentThread().getName());

		TimeUnit.SECONDS.sleep(3);

		return "normal";
	}
}
