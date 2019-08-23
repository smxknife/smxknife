package com.smxknife.java2.thread.futureAndCallable.executeAndSubmit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2019-08-22
 */
public class _Run_ExecuteException {
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(3);

		try {
			service.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("execute running");
					Integer.parseInt("a");
				}
			});
		} catch (Exception e) {
			// 这里永远不会被捕获到
			System.out.println("异常捕获到了：" + e.getMessage());
		}

		service.shutdown();
	}
}
