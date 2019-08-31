package com.smxknife.java2.thread.forkjoin.demo03;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author smxknife
 * 2019/8/31
 */
public class _Run_RecursiveTask_get_Exception {
	public static void main(String[] args) {
		try {
			ForkJoinPool pool = new ForkJoinPool();
			RecursiveTask03_Exception task03 = new RecursiveTask03_Exception();
			System.out.println(task03.hashCode());

			ForkJoinTask<Integer> forkJoinTask = pool.submit(task03);
			System.out.println(forkJoinTask.hashCode() + " " + forkJoinTask.get());
		} catch (InterruptedException e) {
			System.out.println("enter interrupt");
			e.printStackTrace();
		} catch (ExecutionException e) {
			// get方法获取返回值，任务遇到异常情况是可以在ExecutionException中进行捕获的
			System.out.println("enter execution");
			System.out.println("// get方法获取返回值，任务遇到异常情况是可以在ExecutionException中进行捕获的");
			e.printStackTrace();
		}

		System.out.println("main end");

	}
}
