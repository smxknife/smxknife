package com.smxknife.java2.thread.futureAndCallable.cancel;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019-08-19
 */
public class RunnableInterrupt implements Runnable {
	@Override
	public void run() {
		while (true) {
			System.out.println("Running....");
			if (Thread.currentThread().isInterrupted()) {
				try {
					throw new InterruptedException("xxxxxx");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			}

			try {
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
