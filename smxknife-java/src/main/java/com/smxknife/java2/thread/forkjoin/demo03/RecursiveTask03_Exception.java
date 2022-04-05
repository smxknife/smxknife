package com.smxknife.java2.thread.forkjoin.demo03;

import java.util.concurrent.RecursiveTask;

/**
 * @author smxknife
 * 2019/8/31
 */
public class RecursiveTask03_Exception extends RecursiveTask<Integer> {
	@Override
	protected Integer compute() {
		System.out.println(Thread.currentThread().getName() + " compute!!");
		Integer.parseInt("a");
		return 100;
	}
}
