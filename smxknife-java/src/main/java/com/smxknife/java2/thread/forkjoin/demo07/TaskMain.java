package com.smxknife.java2.thread.forkjoin.demo07;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author smxknife
 * 2020/5/29
 */
public class TaskMain {
	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		ForkSumTask forkSumTask = new ForkSumTask(1, 100);
		ForkJoinTask<Integer> submit = forkJoinPool.submit(forkSumTask);
		Integer join = submit.join();
		System.out.println(join);
	}
}
