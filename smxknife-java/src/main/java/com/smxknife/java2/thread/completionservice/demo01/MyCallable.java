package com.smxknife.java2.thread.completionservice.demo01;

import lombok.AllArgsConstructor;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/27
 */
@AllArgsConstructor
public class MyCallable implements Callable<String> {

	private String userName;
	private long sleepValue;

	@Override
	public String call() throws Exception {
		System.out.println("userName = " + userName);
		TimeUnit.SECONDS.sleep(sleepValue);
		return userName + ", " + sleepValue;
	}
}
