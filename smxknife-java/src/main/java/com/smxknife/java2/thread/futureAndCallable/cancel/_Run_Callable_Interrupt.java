package com.smxknife.java2.thread.futureAndCallable.cancel;

import java.util.concurrent.*;

/**
 * @author smxknife
 * 2019-08-19
 */
public class _Run_Callable_Interrupt {
	public static void main(String[] args) {
		CallableInterrupt callableInterrupt = new CallableInterrupt();

		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10,
				5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
		Future<?> future = executor.submit(callableInterrupt);

		try {
			TimeUnit.SECONDS.sleep(2);
			System.out.println(future.cancel(false));
//			System.out.println(future.cancel(false));
			System.out.println(future.isCancelled());
			future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println("xxxxxxx");
			e.printStackTrace();
		}


		executor.shutdown();
	}
}
