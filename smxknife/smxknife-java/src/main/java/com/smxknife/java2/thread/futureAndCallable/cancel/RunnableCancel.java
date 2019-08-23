package com.smxknife.java2.thread.futureAndCallable.cancel;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019-08-19
 */
public class RunnableCancel implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
