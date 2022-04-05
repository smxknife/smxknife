package com.smxknife.java2.thread.forkjoin.demo03;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/31
 */
public class _Run_RecursiveTask_join {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();
		RecursiveTask03 task03 = new RecursiveTask03();
		System.out.println(task03.hashCode());

		ForkJoinTask<Integer> forkJoinTask = pool.submit(task03);
		// join与get都可以取到值，但是在处理异常时有些区别
		System.out.println(forkJoinTask.hashCode() + " " + forkJoinTask.join());

		TimeUnit.SECONDS.sleep(5);

	}
}
