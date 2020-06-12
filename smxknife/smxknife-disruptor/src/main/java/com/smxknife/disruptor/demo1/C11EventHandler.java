package com.smxknife.disruptor.demo1;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * 事件处理类，EventHandler
 * WorkHandler是为了池化
 * @author smxknife
 * 2020/2/25
 */
public class C11EventHandler implements EventHandler<LongEvent>, WorkHandler<LongEvent> {
	@Override
	public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
		Long number = longEvent.getNumber();
		number += 10;
		System.out.println(System.currentTimeMillis() + " : C11 Consumer finish number = " + number);
	}

	@Override
	public void onEvent(LongEvent longEvent) throws Exception {
		Long number = longEvent.getNumber();
		number += 10;
		System.out.println(System.currentTimeMillis() + " : C11 Consumer finish number = " + number);
	}
}
