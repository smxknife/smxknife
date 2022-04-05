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

import static org.apache.flink.table.api.Expressions.*;

/**
 * @author smxknife
 * 2020/9/14
 */
public class _14_TableOverWindow {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

		// 第一种方式就是利用DataStream的时间窗口
		DataStream<Sensor> dataStream = env.readTextFile(_14_TableOverWindow.class.getResource("/Sensor.csv").getPath())
				.map((input) -> {
					String[] array = input.split(",");
					return new Sensor(array[0], Double.valueOf(array[1]), Long.valueOf(array[2]));
				})
				.assignTimestampsAndWatermarks(WatermarkStrategy.<Sensor>forBoundedOutOfOrderness(Duration.ofSeconds(1)).withTimestampAssigner((sensor, processTime) -> sensor.getTimestamp()));

		Table table = tableEnv.fromDataStream(dataStream,  $("id"), $("value"), $("timestamp"), $("event_time").rowtime(), $("process_time").proctime());

		// Over window是针对每个输入行，计算相邻行范围的聚合
		// 1. 无界UNBOUNDED_RANGE，基于时间无范围
		Table table1 = table.window(Over.partitionBy($("id")).orderBy($("event_time")).preceding(UNBOUNDED_RANGE).as("w"))
				.select($("id"), $("value").sum().over($("w")), $("event_time"));
			// 因为这里开窗了，所以不会更新数据
		//tableEnv.toAppendStream(table1, Row.class).print("table1");

		// 2. 无界UNBOUNDED_ROW，基于行数无范围
		Table table2 = table.window(Over.partitionBy($("id")).orderBy($("event_time")).preceding(UNBOUNDED_ROW).as("w"))
				.select($("id"), $("value").sum().over($("w")), $("event_time"));
		//tableEnv.toAppendStream(table2, Row.class).print("table2");

		// 3. 有界BOUNDED_RANGE
		Table table3 = table.window(Over.partitionBy($("id")).orderBy($("event_time")).preceding(lit(5).seconds()).as("w"))
				.select($("id"), $("value").sum().over($("w")));
		// tableEnv.toAppendStream(table3, Row.class).print("table3");

		// 4. 有界BOUNDED_ROW，rowInterval是指起始位置中间隔三行，意思是每4行汇总一次
		Table table4 = table.window(Over.partitionBy($("id")).orderBy($("event_time")).preceding(rowInterval(3L)).as("w"))
				.select($("id"), $("id").count().over("w"), $("value").sum().over($("w")), $("value").min().over($("w")), $("value").max().over($("w")), $("value").avg().over($("w")));
		tableEnv.toAppendStream(table4, Row.class).print("table4");


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
