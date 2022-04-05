package com.smxknife.flink.demo.demo04;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * @author smxknife
 * 2020/8/14
 */
public class FromKafkaDemo {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.put("zookeeper.connect","localhost:2181");
		properties.put("group.id","flink-group");
		properties.put("auto.offset.reset","latest");

		//数据源配置，是一个kafka消息的消费者
		FlinkKafkaConsumer010 consumer010 = new FlinkKafkaConsumer010("test", new SimpleStringSchema(), properties);

		executionEnvironment.addSource(consumer010)
				.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
					@Override
					public void flatMap(String o, Collector<Tuple2<String, Integer>> collector) throws Exception {
						collector.collect(new Tuple2<>(o, 1));
					}
				}).keyBy(new KeySelector<Tuple2<String, Integer>, String>() {
					@Override
					public String getKey(Tuple2<String, Integer> o) throws Exception {
						return o.f0;
					}
				})
				.timeWindow(Time.seconds(1))
				.apply(new WindowFunction<Tuple2<String, Integer>, Tuple2<String, Integer>, String, TimeWindow>() {
					@Override
					public void apply(String s, TimeWindow timeWindow, Iterable<Tuple2<String, Integer>> input, Collector<Tuple2<String, Integer>> collector) throws Exception {
						int sum = 0;
						for (Tuple2<String, Integer> record : input) {
							sum += record.f1;
						}

						Tuple2<String, Integer> result = input.iterator().next();
						result.f1 = sum;
						collector.collect(result);
					}
				}).print();

		executionEnvironment.execute("Flink-Kafka");
	}
}
