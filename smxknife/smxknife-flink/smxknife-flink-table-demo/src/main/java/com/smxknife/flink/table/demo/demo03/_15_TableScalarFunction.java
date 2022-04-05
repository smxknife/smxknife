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
import org.apache.flink.table.functions.ScalarFunction;

import java.sql.Timestamp;
import java.time.*;

import static org.apache.flink.table.api.Expressions.$;
import static org.apache.flink.table.api.Expressions.call;

/**
 * @author smxknife
 * 2020/9/15
 */
public class _15_TableScalarFunction {
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

		// Table API方式
		LocalDataTimeScalarFunction localDataTime = new LocalDataTimeScalarFunction();
		Utc2LocalScalarFunction utc2Local = new Utc2LocalScalarFunction();
		Table table1 = table.select($("id"), call(localDataTime, $("timestamp")).as("tm"), call(utc2Local, $("event_time")).as("local"), $("event_time").as("utc"), $("value"));
		// table1.execute().print();

		tableEnv.createTemporaryView("sensor", table);
		tableEnv.registerFunction("utc2local", utc2Local);
		Table table2 = tableEnv.sqlQuery("select id, utc2local(event_time), `value` from sensor");
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

	// 标量函数
	public static class LocalDataTimeScalarFunction extends ScalarFunction {

		public LocalDateTime eval(Long eventTime) {
			return LocalDateTime.ofInstant(Instant.ofEpochMilli(eventTime), ZoneId.systemDefault());
		}
	}

	public static class Utc2LocalScalarFunction extends ScalarFunction {
		public LocalDateTime eval(Timestamp eventTime) {
			LocalDateTime localDateTime = eventTime.toLocalDateTime();
			LocalDateTime localDateTime1 = localDateTime.atZone(ZoneId.systemDefault()).toLocalDateTime();
			// 这是正确转换时区的方式，先指定localDateTime确定的时区，然后转换为对应的时区
			LocalDateTime localDateTime2 = LocalDateTime.ofInstant(localDateTime.toInstant(ZoneOffset.UTC), ZoneId.systemDefault());
			//System.out.println("org: " + localDateTime + ", org2def: " + localDateTime1 + ", org2offset: " + localDateTime2);
			return localDateTime2;
		}
	}
}
