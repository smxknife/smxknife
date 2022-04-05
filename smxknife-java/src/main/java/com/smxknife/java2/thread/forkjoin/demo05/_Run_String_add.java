package com.smxknife.java2.thread.forkjoin.demo05;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/31
 */
public class _Run_String_add {
	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		RecursiveTask05 task05 = new RecursiveTask05("main", 1, 20);
		ForkJoinTask<String> submit = pool.submit(task05);
		String val = submit.join();
		System.out.println("final: val = " + val);

		TimeUnit.SECONDS.sleep(10);
	}
}
