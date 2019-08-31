package com.smxknife.java2.thread.forkjoin.demo04;

import java.time.LocalTime;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/31
 */
public class RecursiveTask04B extends RecursiveTask<Integer> {
	@Override
	protected Integer compute() {
		System.out.println("B: begin " + Thread.currentThread().getName() + " " + LocalTime.now());
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("B: end " + Thread.currentThread().getName() + " " + LocalTime.now());
		return 200;
	}
}
