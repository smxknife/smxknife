package com.smxknife.java2.thread.futureAndCallable.cancel;

import java.util.concurrent.*;

/**
 * @author smxknife
 * 2019-08-19
 */
public class _Run_Runnable_Interrupt {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		RunnableInterrupt runnableCancel = new RunnableInterrupt();

		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
				5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
		Future<?> future = executor.submit(runnableCancel);

		TimeUnit.SECONDS.sleep(2);

		System.out.println(future.cancel(true));
		System.out.println(future.isCancelled());

		executor.shutdown();
	}
}
