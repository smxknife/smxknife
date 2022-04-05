package com.smxknife.java2.thread.futureAndCallable.cancel;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019-08-19
 */
public class CallableCancel implements Callable<String> {
	@Override
	public String call() {
		System.out.println(Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "";
	}
}
