package com.smxknife.java2.thread.designpattern._10TwoPhaseTermination.demo;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/26
 */
public class CountupThread extends Thread {

	// 计数值
	private long counter = 0;

	// 线程终止状态
	private volatile boolean shutdownRequested = false;

	// 终止线程
	public void shutdownRequest() {
		shutdownRequested = true;
		interrupt();
	}

	@Override
	public void run() {
		try {
			while (!shutdownRequested) {
				doWork();
			}
		} catch (InterruptedException e) {
		} finally {
			doShutdown();
		}
	}

	private void doShutdown() {
		System.out.println("doShutdown counter = " + counter);
	}

	private void doWork() throws InterruptedException {
		counter++;
		System.out.println("doWork counter = " + counter);
		TimeUnit.MILLISECONDS.sleep(500);
	}
}
