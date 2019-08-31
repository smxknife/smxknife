package com.smxknife.java2.thread.executorservice.demo02;

import java.util.concurrent.Callable;

/**
 * @author smxknife
 * 2019/8/29
 */
public class CallableException implements Callable<String> {
	@Override
	public String call() throws Exception {

		System.out.println("exception: " + Thread.currentThread().getName());

		if (1 == 1) {
			throw new Exception("xxx");
		}

		return "exception";
	}
}
