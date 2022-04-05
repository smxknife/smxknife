package com.smxknife.flink.demo._transformation;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.IterativeStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author smxknife
 * 2021/6/3
 */
public class IteratorOperator {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

		final DataStreamSource<Long> stream = executionEnvironment.generateSequence(1, 100);

		final IterativeStream<Long> iterativeStream = stream.iterate();

		final SingleOutputStreamOperator<Long> iterateBody = iterativeStream.map(new MapFunction<Long, Long>() {
			@Override
			public Long map(Long aLong) throws Exception {
				return aLong - 1;
			}
		});
		final SingleOutputStreamOperator<Long> feedback = iterateBody
				.filter(new FilterFunction<Long>() {
					@Override
					public boolean filter(Long aLong) throws Exception {
						return aLong > 0;
					}
				});

		iterativeStream.closeWith(feedback);

		iterativeStream.print();


		executionEnvironment.execute();
	}
}
