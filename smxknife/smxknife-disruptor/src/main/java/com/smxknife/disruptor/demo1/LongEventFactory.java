package com.smxknife.disruptor.demo1;

import com.lmax.disruptor.EventFactory;

/**
 * 事件工厂类
 * @author smxknife
 * 2020/2/25
 */
public class LongEventFactory implements EventFactory<LongEvent> {
	@Override
	public LongEvent newInstance() {
		return new LongEvent();
	}
}
