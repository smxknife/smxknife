package com.smxknife.disruptor.demo3_multi_consumer;

import com.lmax.disruptor.EventFactory;

/**
 * @author smxknife
 * 2020/3/5
 */
public class ReadEventFactory implements EventFactory<ReadEvent> {
	@Override
	public ReadEvent newInstance() {
		return new ReadEvent();
	}
}
