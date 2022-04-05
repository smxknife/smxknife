package com.smxknife.flink.demo._transformation;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoFlatMapFunction;
import org.apache.flink.util.Collector;

/**
 * @author smxknife
 * 2021/6/3
 */
public class CoFlatMapOperator {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

		final DataStreamSource<Integer> stream0 = executionEnvironment.fromElements(1, 2, 3, 4, 5, 6);
		final DataStreamSource<Tuple2<String, Integer>> stream1 = executionEnvironment.fromElements(Tuple2.of("kafka", 3), Tuple2.of("java", 4));

		// connect不是将两个流进行合并，而是将两个流的结果各自放到同一个流的两个参数中，如下
		final ConnectedStreams<Integer, Tuple2<String, Integer>> connect = stream0.connect(stream1);

		connect.flatMap(new CoFlatMapFunction<Integer, Tuple2<String, Integer>, String>() {
			@Override
			public void flatMap1(Integer integer, Collector<String> collector) throws Exception {
				collector.collect(integer + " | flatMap1");
			}

			@Override
			public void flatMap2(Tuple2<String, Integer> tuple2, Collector<String> collector) throws Exception {
				collector.collect(tuple2.f0 + " | " + tuple2.f1);
			}
		}).print().setParallelism(1);

		executionEnvironment.execute();
	}
}
