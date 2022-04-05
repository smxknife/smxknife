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
public class _13_1_TableGroupSQLWindow {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

		// 第一种方式就是利用DataStream的时间窗口
		DataStream<Sensor> dataStream = env.readTextFile(_13_1_TableGroupSQLWindow.class.getResource("/Sensor.csv").getPath())
				.map((input) -> {
					String[] array = input.split(",");
					return new Sensor(array[0], Double.valueOf(array[1]), Long.valueOf(array[2]));
				})
				.assignTimestampsAndWatermarks(WatermarkStrategy.<Sensor>forBoundedOutOfOrderness(Duration.ofSeconds(1)).withTimestampAssigner((sensor, processTime) -> sensor.getTimestamp()));

		Table table = tableEnv.fromDataStream(dataStream,  $("id"), $("value"), $("timestamp"), $("event_time").rowtime(), $("process_time").proctime());

		tableEnv.createTemporaryView("sensor", table);

		Table table1 = tableEnv.sqlQuery("select id, count(id), sum(`value`) as val, tumble_end(event_time, interval '2' second) as w from sensor group by tumble(event_time, interval '2' second), id");

		tableEnv.toAppendStream(table1, Row.class).print("table1");

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
