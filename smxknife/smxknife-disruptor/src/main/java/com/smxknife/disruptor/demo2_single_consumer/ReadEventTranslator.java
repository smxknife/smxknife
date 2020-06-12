package com.smxknife.disruptor.demo2_single_consumer;

import com.lmax.disruptor.EventTranslatorTwoArg;

import java.time.LocalDateTime;

/**
 * @author smxknife
 * 2020/3/6
 */
public class ReadEventTranslator implements EventTranslatorTwoArg<ReadEvent, LocalDateTime, Double> {
	@Override
	public void translateTo(ReadEvent readEvent, long l, LocalDateTime localDateTime, Double val) {
		readEvent.setLocalDateTime(localDateTime);
		readEvent.setValue(val);
	}
}
