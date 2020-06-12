package com.smxknife.java2.thread.forkjoin.demo07;

import java.util.concurrent.RecursiveTask;

/**
 * @author smxknife
 * 2020/5/29
 */
public class ForkSumTask extends RecursiveTask<Integer> {
	private int begin;
	private int end;

	public ForkSumTask(int begin, int end) {
		this.begin = begin;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = end + begin;
		int mid = sum / 2;
		StringBuilder builder = new StringBuilder(Thread.currentThread().getName() + " | ")
				.append(String.format("begin = %s, end = %s, mid = %s | ", begin, end, mid));
		if (mid - begin > 0) {
			builder.append(String.format("leftBegin = %s, leftEnd = %s | ", begin, mid));
			builder.append(String.format("rightBegin = %s, rightEnd = %s", mid + 1, end));
			System.out.println(builder.toString());
			ForkSumTask left = new ForkSumTask(begin, mid);
			ForkSumTask right = new ForkSumTask(mid + 1, end);
			invokeAll(left, right);
			return left.join() + right.join();
		} else {
			System.out.printf( "%s_compute() begin = %s, end = %s\r\n", Thread.currentThread().getName(), begin, end);
			return begin == end ? begin : begin + end;
		}
	}
}
