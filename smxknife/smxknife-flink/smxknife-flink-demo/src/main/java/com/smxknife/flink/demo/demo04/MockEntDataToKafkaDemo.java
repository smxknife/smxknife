package com.smxknife.flink.demo.demo04;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * @author smxknife
 * 2020/8/14
 */
public class MockEntDataToKafkaDemo {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.put("zookeeper.connect","localhost:2181");
		properties.put("group.id","flink-group");
		properties.put("auto.offset.reset","latest");

		EntDataRichParallelSourceFunction entDataSourceFunction = new EntDataRichParallelSourceFunction();

		FlinkKafkaProducer010 producer010 = new FlinkKafkaProducer010("ent-data", new SimpleStringSchema(), properties);

		executionEnvironment.addSource(entDataSourceFunction)
				.flatMap(new FlatMapFunction<JSONObject, JSONObject>() {
					@Override
					public void flatMap(JSONObject data, Collector<JSONObject> collector) throws Exception {
						String entCode = data.getString("entCode");
						JSONArray dataJSONArray = data.getJSONArray("data");
						for (int i = 0; i < dataJSONArray.size(); i++) {
							JSONObject jsonObject = dataJSONArray.getJSONObject(i);
							jsonObject.put("entCode", entCode);
							collector.collect(jsonObject);
						}
					}
				}).map(new MapFunction<JSONObject, String>() {
					@Override
					public String map(JSONObject jsonObject) throws Exception {
						return jsonObject.toJSONString();
					}
				})
				.addSink(producer010);

		executionEnvironment.execute("Flink-Kafka-Ent-data");
	}
}
