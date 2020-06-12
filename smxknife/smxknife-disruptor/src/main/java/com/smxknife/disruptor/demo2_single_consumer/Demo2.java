package com.smxknife.disruptor.demo2_single_consumer;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2020/3/5
 */
public class Demo2 {
	public static void main(String[] args) {
		int ringBufferSize = 1024;

		ReadEventFactory readEventFactory = new ReadEventFactory();

		Disruptor<ReadEvent> disruptor = new Disruptor(readEventFactory,
				ringBufferSize,
				Executors.defaultThreadFactory(),
				ProducerType.SINGLE,
				new BlockingWaitStrategy());

		// disruptor.handleEventsWith(new ReadEventHandler());
		int threadCount = Runtime.getRuntime().availableProcessors();
		ReadEventHandler[] handlers = IntStream.iterate(0, idx -> idx + 1).limit(threadCount)
				.mapToObj(idx -> new ReadEventHandler()).toArray(ReadEventHandler[]::new);
		disruptor.handleEventsWith(handlers);
		RingBuffer<ReadEvent> ringBuffer = disruptor.start();

		IntStream.iterate(0, idx -> idx + 1).limit(100).forEach(idx -> {
			ringBuffer.publishEvent(new ReadEventTranslator(), LocalDateTime.now(), 10.0 + idx);
		});
	}
}
