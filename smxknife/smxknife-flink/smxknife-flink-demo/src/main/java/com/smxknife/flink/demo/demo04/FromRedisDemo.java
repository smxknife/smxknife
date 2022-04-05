package com.smxknife.flink.demo.demo04;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author smxknife
 * 2020/9/1
 */
public class FromRedisDemo {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		DataStreamSource<String> dataStreamSource = env.socketTextStream("localhost", 9000);
		dataStreamSource.map(new MapFunction<String, Tuple2<String, Integer>>() {
			@Override
			public Tuple2<String, Integer> map(String data) throws Exception {
				String[] array = data.split(" ");
				return new Tuple2<>(data, array.length);
			}
		});

		// TODO 暂时搁置

		env.execute("redis job");
	}
}
