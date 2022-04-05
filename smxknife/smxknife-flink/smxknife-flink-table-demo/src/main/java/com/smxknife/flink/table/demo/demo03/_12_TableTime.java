package com.smxknife.flink.table.demo.demo03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.time.Duration;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author smxknife
 * 2020/9/14
 */
public class _12_TableTime {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

		// 第一种方式就是利用DataStream的时间窗口
		// 还有两种方式就是withSchema和sql的方式，这两种方式比较麻烦，这里没有写明
		DataStream<Sensor> dataStream = env.readTextFile(_12_TableTime.class.getResource("/Sensor.csv").getPath())
				.map((input) -> {
					String[] array = input.split(",");
					return new Sensor(array[0], Double.valueOf(array[1]), Long.valueOf(array[2]));
				})
				.assignTimestampsAndWatermarks(WatermarkStrategy.<Sensor>forBoundedOutOfOrderness(Duration.ofSeconds(2)).withTimestampAssigner((sensor, processTime) -> sensor.getTimestamp()));

		Table table = tableEnv.fromDataStream(dataStream,  $("id"), $("value"), $("timestamp"), $("event_time").rowtime(), $("process_time").proctime());

		table.printSchema();

		tableEnv.toAppendStream(table, Row.class).print("appendStream");

		env.execute("table time test job");
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
