package com.smxknife.java2.thread.forkjoin.demo07;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * @author smxknife
 * 2020/5/29
 */
public class ForkAction extends RecursiveAction {
	private int begin;
	private int end;

	public ForkAction(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	@Override
	protected void compute() {
		int diff = end - begin;
		if (diff > 2) {
			int mid = diff / 2;
			ForkAction left = new ForkAction(begin, mid);
			ForkAction right = new ForkAction(mid + 1, end);
			ForkJoinTask<Void> fork1 = left.fork();
			ForkJoinTask<Void> fork2 = right.fork();
//			fork1.join();
//			fork2.join();
//			invokeAll(fork1, fork2);
			System.out.println("注意：fork的操作就是把分解出来的任务分片提交到任务队列中，fork之后呢是不能再调用invokeAll，因为invoke里面会再fork一次，所以，会出现重复的任务分片");
		} else {
			System.out.printf( "%s_compute() begin = %s, end = %s\r\n", Thread.currentThread().getName(), begin, end);
		}
	}
}
