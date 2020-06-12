package com.smxknife.disruptor.demo1;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * @author smxknife
 * 2020/2/25
 */
public class C22EventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
	@Override
	public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
		long number = longEvent.getNumber();
		number *= 20;
		System.out.println(System.currentTimeMillis()+": c2-2 consumer finished.number=" + number);
	}

	@Override
	public void onEvent(LongEvent longEvent) throws Exception {
		long number = longEvent.getNumber();
		number *= 20;
		System.out.println(System.currentTimeMillis()+": c2-2 consumer finished.number=" + number);
	}
}
