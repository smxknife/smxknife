package com.smxknife.java2.thread.forkjoin.demo07;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/5/29
 */
public class ActionMain {
	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkAction forkAction = new ForkAction(0, 5);
		forkJoinPool.submit(forkAction);
		TimeUnit.SECONDS.sleep(5);
	}
}
