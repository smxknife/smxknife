package com.smxknife.flink.demo01;

import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.util.Objects;

/**
 * @author smxknife
 * 2020/4/24
 */
public class FraudDetector extends KeyedProcessFunction<Long, Transaction, Alert> {

	private static final long serialVersionUID = 1L;
	private transient ValueState<Boolean> flagState;
	private transient ValueState<Long> timerState;

	private static final double SMALL_AMOUNT = 1.00;
	private static final double LARGE_AMOUNT = 500.00;
	private static final long ONE_MINUTE = 60 * 1000;

	@Override
	public void processElement(Transaction transaction, Context context, Collector<Alert> collector) throws Exception {
		Boolean lastTransactionWasSmall = flagState.value();
		if (Objects.nonNull(lastTransactionWasSmall)) {
			if (transaction.getVal() > LARGE_AMOUNT) {
				Alert alert = new Alert();
				alert.setId(transaction.getAccountId());
				alert.setVal(transaction.getVal());
				collector.collect(alert);
			}
			flagState.clear();
		}

		if (transaction.getVal() < SMALL_AMOUNT) {
			// Set the flag to true
			flagState.update(true);

			// set the timer and timer state
			long timer = context.timerService().currentProcessingTime() + ONE_MINUTE;
			context.timerService().registerProcessingTimeTimer(timer);
			timerState.update(timer);
		}
	}

	@Override
	public void open(Configuration parameters) throws Exception {
		ValueStateDescriptor<Boolean> flagDescriptor = new ValueStateDescriptor<>("flag", Types.BOOLEAN);
		flagState = getRuntimeContext().getState(flagDescriptor);

		ValueStateDescriptor<Long> timerDescriptor = new ValueStateDescriptor<>(
				"timer-state",
				Types.LONG);
		timerState = getRuntimeContext().getState(timerDescriptor);
	}

	@Override
	public void onTimer(long timestamp, OnTimerContext ctx, Collector<Alert> out) throws Exception {
		timerState.clear();
		flagState.clear();
	}
}
