package com.smxknife.disruptor.demo3_multi_consumer;

import com.lmax.disruptor.LifecycleAware;
import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/3/5
 */
public class ReadEventHandler implements WorkHandler<ReadEvent>, LifecycleAware {

	@Override
	public void onEvent(ReadEvent readEvent) throws Exception {
		System.out.printf("Thread = %s, readEvent = %s \r\n", Thread.currentThread().getName(), readEvent);
		TimeUnit.SECONDS.sleep(2);
	}

	@Override
	public void onStart() {
		System.out.printf("---- Thread = %s start \r\n", Thread.currentThread().getName());
	}

	@Override
	public void onShutdown() {
		System.out.printf("==== Thread = %s end \r\n", Thread.currentThread().getName());
	}
}
