package com.smxknife.flink.table.demo.demo03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.catalog.DataTypeFactory;
import org.apache.flink.table.functions.TableAggregateFunction;
import org.apache.flink.table.types.inference.TypeInference;
import org.apache.flink.table.types.inference.TypeStrategies;
import org.apache.flink.util.Collector;

import java.time.Duration;

import static org.apache.flink.table.api.Expressions.$;
import static org.apache.flink.table.api.Expressions.call;

/**
 * @author smxknife
 * 2020/9/16
 */
public class _018_TableTableAggregateFunction {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		env.setParallelism(1);

		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

		// 第一种方式就是利用DataStream的时间窗口
		DataStream<Sensor> dataStream = env.readTextFile(_018_TableTableAggregateFunction.class.getResource("/Sensor.csv").getPath())
				.map((input) -> {
					String[] array = input.split(",");
					return new Sensor(array[0], Double.valueOf(array[1]), Long.valueOf(array[2]));
				})
				.assignTimestampsAndWatermarks(WatermarkStrategy.<Sensor>forBoundedOutOfOrderness(Duration.ofSeconds(1)).withTimestampAssigner((sensor, processTime) -> sensor.getTimestamp()));

		Table table = tableEnv.fromDataStream(dataStream,  $("id"), $("value"), $("timestamp"), $("event_time").rowtime(), $("process_time").proctime());

		// 可以用来实现TopN

		// API的方式同样报错，在方法里面直接抛出异常
		Table table1 = table.groupBy($("id"))
				.flatAggregate(call(SensorTopNTableAggregateFunction.class, $("value")))
				.select($("id"), $("f0"), $("f1"));

		table1.execute().print();

//		tableEnv.createTemporaryView("sensor", table);
//
//		tableEnv.registerFunction("top2F", new SensorTopNTableAggregateFunction());
//		Table table2 = tableEnv.sqlQuery("select id, top2F(`value`).firstVal from sensor group by id");
//		table2.execute().print();

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

	// 自定义状态类
	public static class SensorTop2Accumulator {
		public Double firstVal;
		public Double secondVal;
	}

	public static class SensorTopNTableAggregateFunction extends TableAggregateFunction<Double, SensorTop2Accumulator> {

		@Override
		public SensorTop2Accumulator createAccumulator() {
			return new SensorTop2Accumulator();
		}

		public void accumulate(SensorTop2Accumulator acc, Double value) {

			if (Double.compare(acc.firstVal, value) > 0) {
				acc.secondVal = acc.firstVal;
				acc.firstVal = value;
			} else if (Double.compare(acc.secondVal, value) > 0) {
				acc.secondVal = value;
			}
		}

		// 实现一个输出数据方法，写到结果表
		public void emitValue(SensorTop2Accumulator acc, Collector<Tuple2<Double, Integer>> collector) {
			collector.collect(new Tuple2<>(acc.firstVal, 1));
			collector.collect(new Tuple2<>(acc.secondVal, 2));
		}

		@Override
		public TypeInference getTypeInference(DataTypeFactory typeFactory) {
			return TypeInference.newBuilder()
					// specify typed arguments
					// parameters will be casted implicitly to those types if necessary
					.typedArguments(DataTypes.DOUBLE())
					// specify a strategy for the result data type of the function
					.outputTypeStrategy(TypeStrategies.MISSING)
					.build();
		}
	}
}
