package com.smxknife.java2.thread.futureAndCallable.getTimeout;

import lombok.AllArgsConstructor;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019-08-22
 */
@AllArgsConstructor
public class GetTimeoutCallable implements Callable<String> {

	private String name;

	@Override
	public String call() throws Exception {
		TimeUnit.SECONDS.sleep(10);
		System.out.println("睡醒了...");
		return "name is " + name;
	}
}
