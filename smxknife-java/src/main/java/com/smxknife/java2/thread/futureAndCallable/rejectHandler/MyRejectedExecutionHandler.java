package com.smxknife.java2.thread.futureAndCallable.rejectHandler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author smxknife
 * 2019-08-22
 */
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println(r.toString() + " 被拒绝执行...");
	}
}
