package com.smxknife.flink.demo.demo03;

import org.apache.flink.api.common.functions.CoGroupFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.MapOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author smxknife
 * 2020/8/14
 */
public class CoGroupDemo {
	public static void main(String[] args) throws Exception {
		ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
		DataSource<String> dataSource1 = env.fromElements("hello", "world", "flink");

		MapOperator<String, Tuple2<String, String>> operator1 = dataSource1.map(new MapFunction<String, Tuple2<String, String>>() {
			@Override
			public Tuple2<String, String> map(String input) throws Exception {
				return new Tuple2<>(input, input + "_1");
			}
		});

		DataSource<String> dataSource2 = env.fromElements("hello", "test", "java");

		MapOperator<String, Tuple2<String, String>> operator2 = dataSource2.map(new MapFunction<String, Tuple2<String, String>>() {
			@Override
			public Tuple2<String, String> map(String input) throws Exception {
				return new Tuple2<>(input, input + "_2");
			}
		});

		operator1.coGroup(operator2)
				.where(new KeySelector<Tuple2<String, String>, String>() {
					@Override
					public String getKey(Tuple2<String, String> stringStringTuple2) throws Exception {
						System.out.println("------ " + stringStringTuple2.f0 + " | " + stringStringTuple2.f1 + " | " + stringStringTuple2.toString());
						return stringStringTuple2.f0;
					}
				}).equalTo(new KeySelector<Tuple2<String, String>, String>() {
					@Override
					public String getKey(Tuple2<String, String> stringStringTuple2) throws Exception {
						System.out.println("++++++ " + stringStringTuple2.f0 + " | " + stringStringTuple2.f1 + " | " + stringStringTuple2.toString());
						return stringStringTuple2.f0;
					}
				}).with(new CoGroupFunction<Tuple2<String, String>, Tuple2<String, String>, String>() {
					@Override
					public void coGroup(Iterable<Tuple2<String, String>> iterable, Iterable<Tuple2<String, String>> iterable1, Collector<String> collector) throws Exception {
						iterable.forEach(t -> {
							System.out.println("******* " + t.f0 + " | " + t.f1);
							collector.collect(t.f0 + t.f1);
						});

						iterable1.forEach(t -> {
							System.out.println("####### " + t.f0 + " | " + t.f1);
							collector.collect(t.f0 + t.f1);
						});
					}
				}).print();


	}
}
