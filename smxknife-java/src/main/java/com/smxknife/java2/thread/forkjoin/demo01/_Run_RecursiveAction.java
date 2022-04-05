package com.smxknife.java2.thread.forkjoin.demo01;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/31
 */
public class _Run_RecursiveAction {
	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		pool.submit(new RecursiveActionDemo01());

		TimeUnit.SECONDS.sleep(5);
	}
}
