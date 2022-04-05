package com.smxknife.flink.demo.demo03;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.util.Arrays;

/**
 * @author smxknife
 * 2020/8/11
 */
public class WordCount {
	public static void main(String[] args) throws Exception {
		// 设定执行环节设定
		ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();
		// 指定数据源地址，读取输入数据
		DataSource<String> dataSource = executionEnvironment.readTextFile("file:///Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-flink/src/main/java/com/smxknife/flink/demo03/hello");
		// 对数据集指定转换操作逻辑
//		SingleOutputStreamOperator<Tuple2<String, Integer>> sum = dataStreamSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
//			@Override
//			public void flatMap(String text, Collector<Tuple2<String, Integer>> collector) throws Exception {
//				Arrays.asList(text.split(" ")).forEach(t -> {
//					collector.collect(new Tuple2<>(t, 1));
//				});
//
//			}
//		}).keyBy(0).sum(0);
		AggregateOperator<Tuple2<String, Integer>> sum = dataSource.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
			@Override
			public void flatMap(String val, Collector<Tuple2<String, Integer>> out) throws Exception {
				Arrays.asList(val.split(" ")).forEach(t -> out.collect(new Tuple2<>(t, 1)));
			}
		}).groupBy(0).sum(1);
		// 指定计算结果输出位置
		sum.print();

	}
}
