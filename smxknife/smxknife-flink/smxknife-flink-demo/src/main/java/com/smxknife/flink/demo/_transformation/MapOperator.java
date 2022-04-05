package com.smxknife.flink.demo._transformation;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author smxknife
 * 2021/6/3
 */
public class MapOperator {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

		final DataStreamSource<String> stream = executionEnvironment.fromElements("hello", "kafka", "flink", "nihao abc");

		// map
		final SingleOutputStreamOperator<String> map = stream.map(x -> x + "-------");

		// flatMap
		// 如何使用flatMap代替filter，如果包含abc，过滤掉
		// flatMap的机制，先进行map再进行flat（扁平化），flatMap返回集合
		stream.flatMap(new FlatMapFunction<String, String>() {
			@Override
			public void flatMap(String s, Collector<String> collector) throws Exception {
				if (!s.contains("abc")) {
					collector.collect(s);
				}
			}
		}).print();

		executionEnvironment.execute();
	}
}
