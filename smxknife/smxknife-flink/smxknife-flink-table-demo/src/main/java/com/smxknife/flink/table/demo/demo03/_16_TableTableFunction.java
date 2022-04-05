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

import java.time.Duration;

import static org.apache.flink.table.api.Expressions.$;
import static org.apache.flink.table.api.Expressions.call;

/**
 * @author smxknife
 * 2020/9/15
 */
public class _16_TableTableFunction {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

		// 第一种方式就是利用DataStream的时间窗口
		DataStream<_14_TableOverWindow.Sensor> dataStream = env.readTextFile(_14_TableOverWindow.class.getResource("/Sensor.csv").getPath())
				.map((input) -> {
					String[] array = input.split(",");
					return new _14_TableOverWindow.Sensor(array[0], Double.valueOf(array[1]), Long.valueOf(array[2]));
				})
				.assignTimestampsAndWatermarks(WatermarkStrategy.<_14_TableOverWindow.Sensor>forBoundedOutOfOrderness(Duration.ofSeconds(1)).withTimestampAssigner((sensor, processTime) -> sensor.getTimestamp()));

		Table table = tableEnv.fromDataStream(dataStream,  $("id"), $("value"), $("timestamp"), $("event_time").rowtime(), $("process_time").proctime());


		Table table1 = table
				.joinLateral(call(SensorTableFunction.class, $("id")))
				.select($("id"), $("su1"), $("su2"), $("value"), $("event_time"));
		table1.execute().print();

		tableEnv.createTemporaryView("sensor", table);
		tableEnv.createTemporarySystemFunction("sensorTF", SensorTableFunction.class);
		Table table2 = tableEnv.sqlQuery("select id, su1, su2, `value`, event_time from sensor, lateral table(sensorTF(id))");
		table2.execute().print();

		env.execute("xxx");

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
