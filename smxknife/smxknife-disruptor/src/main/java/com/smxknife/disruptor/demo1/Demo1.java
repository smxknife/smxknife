package com.smxknife.disruptor.demo1;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2020/2/24
 */
public class Demo1 {
	public static void main(String[] args) {
		int bufferSize = 1024 * 1024;

		LongEventFactory eventFactory = new LongEventFactory();

		Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, bufferSize,
				Executors.defaultThreadFactory(),
				ProducerType.SINGLE,
				new BlockingWaitStrategy());

		// 事件处理start
		// parallel(disruptor);
		// serial(disruptor);
		// diamond(disruptor);
		chain(disruptor);
		// 事件处理end

		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		ringBuffer.publishEvent(new LongEventTranslator(), 10L);
		ringBuffer.publishEvent(new LongEventTranslator(), 100L);
	}

	private static void chain(Disruptor<LongEvent> disruptor) {
		disruptor.handleEventsWith(new C11EventHandler()).then(new C12EventHandler());
		disruptor.handleEventsWith(new C21EventHandler()).then(new C22EventHandler());
		disruptor.start();
	}

	private static void diamond(Disruptor<LongEvent> disruptor) {
		disruptor.handleEventsWith(new C11EventHandler(), new C12EventHandler()).then(new C21EventHandler());
		disruptor.start();
	}

	private static void serial(Disruptor<LongEvent> disruptor) {
		disruptor.handleEventsWith(new C11EventHandler(), new C21EventHandler());
		disruptor.start();
	}

	private static void parallel(Disruptor<LongEvent> disruptor) {
		disruptor.handleEventsWith(new C11EventHandler(), new C21EventHandler());
		disruptor.start();
	}

}
