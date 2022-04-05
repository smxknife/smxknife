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
import org.apache.flink.table.functions.AggregateFunction;
import org.apache.flink.types.Row;

import java.time.Duration;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author smxknife
 * 2020/9/15
 */
public class _17_TableAggregateFunction {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

		// 第一种方式就是利用DataStream的时间窗口
		DataStream<Sensor> dataStream = env.readTextFile(_17_TableAggregateFunction.class.getResource("/Sensor.csv").getPath())
				.map((input) -> {
					String[] array = input.split(",");
					return new Sensor(array[0], Double.valueOf(array[1]), Long.valueOf(array[2]));
				})
				.assignTimestampsAndWatermarks(WatermarkStrategy.<Sensor>forBoundedOutOfOrderness(Duration.ofSeconds(1)).withTimestampAssigner((sensor, processTime) -> sensor.getTimestamp()));

		Table table = tableEnv.fromDataStream(dataStream,  $("id"), $("value"), $("timestamp"), $("event_time").rowtime(), $("process_time").proctime());

		// table api 调用
//		Table table1 = table.groupBy($("id"))
//				.select($("id"), $("value"), call(SensorAggregateFunction.class, $("value")));
//		table1.execute().print();

		tableEnv.createTemporaryView("sensor", table);
		// 下面注释的两种方式在该版本始终报错，不知道是不是不支持api写法
//		Table table1 = tableEnv.from("sensor")
//				.groupBy($("id"))
//				.select($("id"), call(SensorAggregateFunction.class, $("value")));
//		table1.execute().print();

//		Table table1 = table.groupBy($("id")).aggregate(call(SensorAggregateFunction.class, new Object[] {$("value")}))
//				.select($("id"));
//		tableEnv.toRetractStream(table1, Row.class).print("api");

		tableEnv.registerFunction("sensorAvgF", new SensorAggregateFunction());
		Table table2 = tableEnv.sqlQuery("select id, sensorAvgF(`value`) as val from sensor group by id");
		tableEnv.toRetractStream(table2, Row.class).print("sql");

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

	// 用于保存聚合状态
	public static class AvgValueAccumulator {
		public Integer count = 0;
		public Double sum = 0.0;
	}

	public static class SensorAggregateFunction extends AggregateFunction<Double, AvgValueAccumulator> {

		@Override
		public Double getValue(AvgValueAccumulator avgValueAccumulator) {
			return avgValueAccumulator.sum / avgValueAccumulator.count;
		}

		@Override
		public AvgValueAccumulator createAccumulator() {
			return new AvgValueAccumulator();
		}

		public void accumulate(AvgValueAccumulator acc, Double value) {
			acc.count++;
			acc.sum += value;
		}

	}
}
