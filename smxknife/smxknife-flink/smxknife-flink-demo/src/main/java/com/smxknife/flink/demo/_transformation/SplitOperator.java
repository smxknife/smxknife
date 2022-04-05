package com.smxknife.flink.demo._transformation;

import org.apache.flink.streaming.api.collector.selector.OutputSelector;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smxknife
 * 2021/6/3
 */
public class SplitOperator {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
		final DataStreamSource<Long> stream = executionEnvironment.generateSequence(1, 100);

		/**
		 * Split可以把一个流拆分为多个流
		 * 需求：
		 *  奇数一个流，偶数一个流
		 */
		final SplitStream<Long> split = stream.split(new OutputSelector<Long>() {
			@Override
			public Iterable<String> select(Long aLong) {
				List<String> list = new ArrayList<>();
				if (aLong % 2 == 0) {
					list.add("even");
				} else {
					list.add("odd");
				}
				return list;
			}
		});

		// split 之后可以通过select选择一个流
		split.select("even").print();


		executionEnvironment.execute();
	}
}
