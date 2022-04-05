package com.smxknife.java2.thread.forkjoin.demo01;

import java.util.concurrent.RecursiveAction;

/**
 * @author smxknife
 * 2019/8/31
 */
public class RecursiveActionDemo01 extends RecursiveAction {
	@Override
	protected void compute() {
		System.out.println("compute | thread is daemon ? " + Thread.currentThread().isDaemon());
	}
}
