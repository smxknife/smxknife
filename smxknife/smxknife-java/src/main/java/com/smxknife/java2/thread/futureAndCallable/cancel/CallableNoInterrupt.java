package com.smxknife.java2.thread.futureAndCallable.cancel;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019-08-19
 */
public class CallableNoInterrupt implements Callable {
	@Override
	public String call() throws Exception {
		while (true) {
			System.out.println("Running....");
			TimeUnit.MILLISECONDS.sleep(500);
		}
	}
}
