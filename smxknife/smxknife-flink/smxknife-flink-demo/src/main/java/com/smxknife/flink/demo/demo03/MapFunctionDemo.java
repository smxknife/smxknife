package com.smxknife.flink.demo.demo03;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.ReduceOperator;

/**
 * @author smxknife
 * 2020/8/12
 */
public class MapFunctionDemo {
	public static void main(String[] args) throws Exception {
		ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
		DataSource<String> dataSource = env.fromElements("hello", "world", "flink");
		classFunction(dataSource);
		anoyClassFunction(dataSource);
		richFunction(dataSource);
	}

	private static void richFunction(DataSource<String> dataSource) throws Exception {
		dataSource.map(new RichMapFunction<String, String>() {
			@Override
			public String map(String s) throws Exception {
				RuntimeContext runtimeContext = getRuntimeContext();
				return s + "-" + runtimeContext.getTaskName();
			}
		}).print();
	}

	private static void anoyClassFunction(DataSource<String> dataSource) throws Exception {
		dataSource.map(new MapFunction<String, String>() {
			@Override
			public String map(String s) throws Exception {
				return s + "*";
			}
		}).print();
	}

	private static void classFunction(DataSource dataSource) throws Exception {
		ReduceOperator reduce = dataSource.map(new MyMapFunction()).reduce((v1, v2) -> {
			return v1.toString() + v2;
		});
		reduce.print();
	}

	static class MyMapFunction implements MapFunction<String, String> {
		@Override
		public String map(String s) throws Exception {
			System.out.println(s);
			return s + "-";
		}
	}
}
