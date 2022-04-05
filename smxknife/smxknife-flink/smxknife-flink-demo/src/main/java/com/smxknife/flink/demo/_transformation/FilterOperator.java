package com.smxknife.flink.demo._transformation;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author smxknife
 * 2021/6/3
 */
public class FilterOperator {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

		final DataStreamSource<String> stream = executionEnvironment.fromElements("hello", "kafka", "flink", "nihao abc");

		// map
		final SingleOutputStreamOperator<String> map = stream.map(x -> x + "-------");

		// 过滤掉abc
		map.filter(x -> !x.contains("abc")).print();
		
		executionEnvironment.execute();
	}
}
