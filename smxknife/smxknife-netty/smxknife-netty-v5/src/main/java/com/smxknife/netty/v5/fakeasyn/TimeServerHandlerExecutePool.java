package com.smxknife.netty.v5.fakeasyn;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2018/11/9
 */
public class TimeServerHandlerExecutePool {

	private ExecutorService executorService;

	public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
		executorService = new ThreadPoolExecutor(
				Runtime.getRuntime().availableProcessors(),
				maxPoolSize,
				120L,
				TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(queueSize));
	}

	public void execute(Runnable runnable) {
		executorService.execute(runnable);
	}
}
