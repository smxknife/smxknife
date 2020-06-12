package com.smxknife.disruptor.demo2_single_consumer;

import com.lmax.disruptor.EventHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/3/5
 */
public class ReadEventHandler implements EventHandler<ReadEvent> {
	@Override
	public void onEvent(ReadEvent readEvent, long l, boolean b) throws Exception {
		System.out.printf("ThreadName = %s, readEvent = %s, l = %d, b = %s \r\n", Thread.currentThread().getName(), readEvent, l, b);
		TimeUnit.SECONDS.sleep(2);
	}
}
