package com.smxknife.java2.thread.forkjoin.demo04;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/31
 */
public class _Run {
	public static void main(String[] args) throws InterruptedException {
		ForkJoinPool pool = new ForkJoinPool();

		ForkJoinTask<Integer> taskA = pool.submit(new RecursiveTask04A());
		ForkJoinTask<Integer> taskB = pool.submit(new RecursiveTask04B());

		System.out.println("准备打印：" + LocalDateTime.now());

		// 从打印结果上看，taskA和taskB是异步执行，但是join是同步阻塞执行
		System.out.println(taskA.join() + " A: " + LocalTime.now());
		System.out.println(taskB.join() + " B: " + LocalTime.now());

		TimeUnit.SECONDS.sleep(10);

		System.out.println("// 从打印结果上看，taskA和taskB是异步执行，但是join是同步阻塞执行");

	}
}
