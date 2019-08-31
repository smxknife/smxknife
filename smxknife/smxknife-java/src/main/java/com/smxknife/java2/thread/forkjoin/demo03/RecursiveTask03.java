package com.smxknife.java2.thread.forkjoin.demo03;

import java.util.concurrent.RecursiveTask;

/**
 * @author smxknife
 * 2019/8/31
 */
public class RecursiveTask03 extends RecursiveTask<Integer> {
	@Override
	protected Integer compute() {
		System.out.println("compute time = " + System.currentTimeMillis());
		return 100;
	}
}
