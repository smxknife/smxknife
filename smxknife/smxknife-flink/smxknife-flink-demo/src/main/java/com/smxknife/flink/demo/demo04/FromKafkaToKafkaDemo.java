package com.smxknife.flink.demo.demo04;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * @author smxknife
 * 2020/8/14
 */
public class FromKafkaToKafkaDemo {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.put("zookeeper.connect","localhost:2181");
		properties.put("group.id","flink-group");
		properties.put("auto.offset.reset","latest");

		//数据源配置，是一个kafka消息的消费者
		FlinkKafkaConsumer010 consumer010 = new FlinkKafkaConsumer010("test", new SimpleStringSchema(), properties);

		FlinkKafkaProducer010 producer010 = new FlinkKafkaProducer010("test2", new SimpleStringSchema(), properties);

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
				.map(new MapFunction<Tuple2<String, Integer>, String>() {
					@Override
					public String map(Tuple2<String, Integer> o) throws Exception {
						return o.f0 + o.f1;
					}
				})
				.addSink(producer010);

		executionEnvironment.execute("Flink-Kafka");
	}
}
