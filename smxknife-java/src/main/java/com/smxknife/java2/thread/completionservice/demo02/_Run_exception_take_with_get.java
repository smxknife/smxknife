package com.smxknife.java2.thread.completionservice.demo02;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2019/8/27
 */
public class _Run_exception_take_with_get {
	public static void main(String[] args) {
		MyCallableA callableA = new MyCallableA();
		MyCallableB callableB = new MyCallableB();

		ExecutorService executor = Executors.newSingleThreadExecutor();
		ExecutorCompletionService completionService = new ExecutorCompletionService<>(executor);

		completionService.submit(callableA);
		completionService.submit(callableB);

		for (int i = 0; i < 2; i++) {
			// 从这里可以看出，take是阻塞的，但是当前例子中，可以跑到最后结束，说明如果存在异常，说明也是运行完毕
			try {
				// 注意这里对比上个例子是调用take()的同时也调用了get，这时候通过输出可以看出，捕获到了任务里面的异常
				System.out.println("--------------- " + completionService.take().get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		System.out.println("main end");
		executor.shutdown();

	}
}
