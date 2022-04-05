package com.smxknife.flink.demo.demo01;

import org.apache.flink.streaming.api.functions.sink.SinkFunction;

/**
 * @author smxknife
 * 2020/4/24
 */
public class AlertSink implements SinkFunction<Alert> {
	@Override
	public void invoke(Alert value, Context context) throws Exception {
		System.out.println("detect fraud transaction, accountId = " + value.getId() + ", value = " + value.getVal());
	}
}
