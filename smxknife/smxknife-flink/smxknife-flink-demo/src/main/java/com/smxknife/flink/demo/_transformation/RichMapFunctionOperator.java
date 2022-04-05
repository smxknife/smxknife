package com.smxknife.flink.demo._transformation;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author smxknife
 * 2021/6/3
 */
public class RichMapFunctionOperator {
	public static void main(String[] args) throws Exception {
		// 可以用于实现将数据写入redis/mysql等其他
		StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
		final DataStreamSource<String> stream = executionEnvironment.fromElements("hello java", "kafka nihao", "flink java", "nihao abc");

		final SingleOutputStreamOperator<Tuple2<String, Integer>> streamOperator = stream.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
			@Override
			public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
				for (String s1 : s.split(" ")) {
					collector.collect(Tuple2.of(s1, 1));
				}
			}
		});

		final SingleOutputStreamOperator<Tuple2<String, Integer>> sum = streamOperator.keyBy(new KeySelector<Tuple2<String, Integer>, String>() {
			@Override
			public String getKey(Tuple2<String, Integer> tuple2) throws Exception {
				return tuple2.f0;
			}
		}).sum(1);

		sum.map(new RichMapFunction<Tuple2<String, Integer>, String>() {

			// 伪代码
			// Jedis jedis = null;

			// subtask启动时执行
			@Override
			public void open(Configuration parameters) throws Exception {
				// jedis = new Jedis("localhost", 6329);
			}

			// 处理每个元素
			@Override
			public String map(Tuple2<String, Integer> tuple2) throws Exception {
				// jedis.set(tuple2.f0, tuple2.f1);
				return tuple2.f0 + "_" + tuple2.f1;
			}

			// subtask结束前执行
			@Override
			public void close() throws Exception {
				// jedis.close();
			}
		}).print();


		executionEnvironment.execute();

	}
}
