package com.smxknife.java2.thread.forkjoin.demo02;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/31
 */
public class _Run_fork {
	// 在调用fork方法时需要注意效率问题，因为每一次调用fork都会分离任务，增加系统运行负担
	// 所以在ForkJoinTask中提供了public static void invokeAll(ForkJoinTask t1, ForkJoinTask t2)来优化执行效率
	public static void main(String[] args) throws InterruptedException {

		System.out.println(0x7fff);

		ForkJoinPool forkJoinPool = new ForkJoinPool();
		forkJoinPool.submit(new RecursiveActionDemo02("main", 1, 10));

		TimeUnit.SECONDS.sleep(5);
	}
}
