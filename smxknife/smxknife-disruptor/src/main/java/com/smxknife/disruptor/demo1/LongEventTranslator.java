package com.smxknife.disruptor.demo1;

import com.lmax.disruptor.EventTranslatorOneArg;

/**
 * 事件转换类
 * @author smxknife
 * 2020/2/25
 */
public class LongEventTranslator implements EventTranslatorOneArg<LongEvent, Long> {
	@Override
	public void translateTo(LongEvent longEvent, long l, Long aLong) {
		longEvent.setNumber(aLong);
	}
}
