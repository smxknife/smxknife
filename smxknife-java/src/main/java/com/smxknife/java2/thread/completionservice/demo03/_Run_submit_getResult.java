package com.smxknife.java2.thread.completionservice.demo03;

import java.util.concurrent.*;

/**
 * @author smxknife
 * 2019/8/27
 */
public class _Run_submit_getResult {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		UserInfo userInfo = new UserInfo();
		MyRunnable myRunnable = new MyRunnable(userInfo);

		ExecutorService executorService = Executors.newCachedThreadPool();
		ExecutorCompletionService runnableExecutorCompletionService = new ExecutorCompletionService(executorService);

		Future<UserInfo> future = runnableExecutorCompletionService.submit(myRunnable, userInfo);
		System.out.println(future.get().getUsername() + " - " + future.get().getPassword());
	}
}
