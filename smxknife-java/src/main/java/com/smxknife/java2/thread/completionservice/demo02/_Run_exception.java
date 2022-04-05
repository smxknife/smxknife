package com.smxknife.java2.thread.completionservice.demo02;

import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2019/8/27
 */
public class _Run_exception {
	public static void main(String[] args) throws InterruptedException {
		MyCallableA callableA = new MyCallableA();
		MyCallableB callableB = new MyCallableB();

		ExecutorService executor = Executors.newSingleThreadExecutor();
		ExecutorCompletionService completionService = new ExecutorCompletionService<>(executor);

		completionService.submit(callableA);
		completionService.submit(callableB);

		for (int i = 0; i < 2; i++) {
			// 从这里可以看出，take是阻塞的，但是当前例子中，可以跑到最后结束，说明如果存在异常，说明也是运行完毕
			System.out.println("--------------- " + completionService.take());
		}

		System.out.println("main end");
		executor.shutdown();

	}
}
