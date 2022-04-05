package com.smxknife.flink.demo.demo05;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.time.Duration;

/**
 * @author smxknife
 * 2020/9/8
 */
public class WithIdlenessDemo {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);

		env.socketTextStream("localhost", 9999)
				.map(new MapFunction<String, String>() {
					@Override
					public String map(String s) throws Exception {
						return s;
					}
				}).assignTimestampsAndWatermarks(WatermarkStrategy.<String>forBoundedOutOfOrderness(Duration.ofSeconds(1))
					.withTimestampAssigner((event, time) -> time)
//						.withIdleness(Duration.ofSeconds(2))
					)
				.keyBy(str -> str)
				.timeWindow(Time.seconds(5))
				.reduce(new ReduceFunction<String>() {
					@Override
					public String reduce(String s, String t1) throws Exception {
						return t1;
					}
				}).print("xxx");

//				.process(new ProcessWindowFunction<String, Tuple2<String, LocalTime>, String, TimeWindow>() {
//					@Override
//					public void process(String key, Context context, Iterable<String> input, Collector<Tuple2<String, LocalTime>> collector) throws Exception {
//						StringBuilder newKey = new StringBuilder();
//						input.forEach(in -> newKey.append(in).append("_"));
//						collector.collect(new Tuple2<>(newKey.toString(), LocalTime.now()));
//					}
//				}).print("xxx");

		env.execute("idleness job");
	}
}
