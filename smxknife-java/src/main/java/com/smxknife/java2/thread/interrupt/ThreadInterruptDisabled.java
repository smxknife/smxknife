package com.smxknife.java2.thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/19
 */
public class ThreadInterruptDisabled implements Runnable {
	@Override
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName() + " | is running" );
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
