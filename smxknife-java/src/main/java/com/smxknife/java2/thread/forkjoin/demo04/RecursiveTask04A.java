package com.smxknife.java2.thread.forkjoin.demo04;

import java.time.LocalTime;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/31
 */
public class RecursiveTask04A extends RecursiveTask<Integer> {
	@Override
	protected Integer compute() {
		System.out.println("A: begin " + Thread.currentThread().getName() + " " + LocalTime.now());
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("A: end " + Thread.currentThread().getName() + " " + LocalTime.now());
		return 100;
	}
}
