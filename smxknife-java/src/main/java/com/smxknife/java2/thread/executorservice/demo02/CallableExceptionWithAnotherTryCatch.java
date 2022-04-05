package com.smxknife.java2.thread.executorservice.demo02;

import java.util.concurrent.Callable;

/**
 * @author smxknife
 * 2019/8/29
 */
public class CallableExceptionWithAnotherTryCatch implements Callable<String> {
	@Override
	public String call() throws Exception {

		try {
			System.out.println("callableExceptionWithAnotherTryCatch: " + Thread.currentThread().getName());

			if (1 == 1) {
				throw new Exception("xxx");
			}
		} catch (Exception ee) {
			throw ee;
		}

		return "callableExceptionWithAnotherTryCatch";
	}
}
