package com.smxknife.flink.demo._transformation;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author smxknife
 * 2021/6/3
 */
public class ReduceOperator {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

		final DataStreamSource<String> stream = executionEnvironment.fromElements("hello java", "java kafka", "flink", "nihao abc");

		// flatMap
		// 如何使用flatMap代替filter，如果包含abc，过滤掉
		// flatMap的机制，先进行map再进行flat（扁平化），flatMap返回集合
		stream.flatMap(new FlatMapFunction<String, String>() {
			@Override
			public void flatMap(String s, Collector<String> collector) throws Exception {
				if (!s.contains("abc")) {
					for (String s1 : s.split(" ")) {
						collector.collect(s1);
					}
				}
			}
			// 这里为什么不采用map(x -> Tuple2.of(x, 1))的形式？因为在使用lambda表达式的时候没有足够的信息推导出Tuple类型，官方建议使用匿名类
		}).map(new MapFunction<String, Tuple2<String, Integer>>() {
			@Override
			public Tuple2<String, Integer> map(String s) throws Exception {
				return Tuple2.of(s, 1);
			}
		}).keyBy(new KeySelector<Tuple2<String, Integer>, Object>() {
			@Override
			public Object getKey(Tuple2<String, Integer> tuple2) throws Exception {
				return tuple2.f0;
			}
		}).reduce(new ReduceFunction<Tuple2<String, Integer>>() {
			@Override
			public Tuple2<String, Integer> reduce(Tuple2<String, Integer> tuple2, Tuple2<String, Integer> t1) throws Exception {
				return Tuple2.of(t1.f0, tuple2.f1 + t1.f1);
			}
		}).print();

		executionEnvironment.execute();
	}
}
