package com.smxknife.flink.table.demo.demo03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.*;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.time.Duration;

import static org.apache.flink.table.api.Expressions.$;
import static org.apache.flink.table.api.Expressions.lit;

/**
 * @author smxknife
 * 2020/9/14
 */
public class _13_TableGroupWindow {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

		// 第一种方式就是利用DataStream的时间窗口
		DataStream<Sensor> dataStream = env.readTextFile(_13_TableGroupWindow.class.getResource("/Sensor.csv").getPath())
				.map((input) -> {
					String[] array = input.split(",");
					return new Sensor(array[0], Double.valueOf(array[1]), Long.valueOf(array[2]));
				})
				.assignTimestampsAndWatermarks(WatermarkStrategy.<Sensor>forBoundedOutOfOrderness(Duration.ofSeconds(1)).withTimestampAssigner((sensor, processTime) -> sensor.getTimestamp()));

		Table table = tableEnv.fromDataStream(dataStream,  $("id"), $("value"), $("timestamp"), $("event_time").rowtime(), $("process_time").proctime());

		// 1. tumble window
		Table tumbleWTable = table.window(Tumble.over(lit(3).seconds()).on($("event_time")).as("w"))
				.groupBy($("w"), $("id"))
				.select($("id"), $("value").sum(), $("w").end());
		tableEnv.toAppendStream(tumbleWTable, Row.class).print("tumbleWTable");

		// 2. slide window
		Table slideWTable = table.window(Slide.over(lit(3).seconds()).every(lit(1).seconds()).on($("event_time")).as("sw"))
				.groupBy($("sw"), $("id"))
				.select($("id"), $("value").sum(), $("sw").end());
		tableEnv.toAppendStream(slideWTable, Row.class).print("slideWTable");

		// 3. session window
		Table sessionWTable = table.window(Session.withGap(lit(5).seconds()).on($("event_time")).as("ssw"))
				.groupBy($("ssw"), $("id"))
				.select($("id"), $("value").sum(), $("ssw").end());
		tableEnv.toAppendStream(sessionWTable, Row.class).print("sessionWTable");

		env.execute("table window test job");
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Sensor {
		private String id;
		private Double value;
		private Long timestamp;
	}
}
