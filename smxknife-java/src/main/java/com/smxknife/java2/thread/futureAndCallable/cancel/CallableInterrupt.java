package com.smxknife.java2.thread.futureAndCallable.cancel;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019-08-19
 */
public class CallableInterrupt implements Callable {
	@Override
	public String call() throws Exception {
		while (true) {
			System.out.println("Running....");
			if (Thread.currentThread().isInterrupted()) {
				System.out.println("iiiii");
				throw new InterruptedException("xxxxxx");
			}
			TimeUnit.MILLISECONDS.sleep(500);
		}
	}
}
