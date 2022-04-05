package com.smxknife.flink.demo._table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.GroupWindowedTable;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.Tumble;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import java.time.Duration;

import static org.apache.flink.table.api.Expressions.$;
import static org.apache.flink.table.api.Expressions.lit;

/**
 * @author smxknife
 * 2021/6/8
 */
public class TableWindowApi {
	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		// 使用flink默认的tablestream，创建Table的上下文环境（TableEnvironment）
		// 注意这里需要指定settings，否则会报错
		final EnvironmentSettings settings = EnvironmentSettings.newInstance().useOldPlanner().build();
		final StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

		final DataStreamSource<String> socketTextStream = env.socketTextStream("localhost", 8888);

		final SingleOutputStreamOperator<Person> personStream = socketTextStream.map(input -> {
			final String[] array = input.split(" ");
			return new Person(array[0].trim(), array[1].trim(), Integer.valueOf(array[2].trim()), Long.valueOf(array[3].trim()));
		}).assignTimestampsAndWatermarks(WatermarkStrategy.<Person>forBoundedOutOfOrderness(Duration.ofSeconds(2)).withTimestampAssigner(new SerializableTimestampAssigner<Person>() {
			@Override
			public long extractTimestamp(Person person, long recordTimestamp) {
				return person.time;
			}
		}));


		final Table table = tableEnv.fromDataStream(personStream, $("id"), $("name"), $("age"), $("time").rowtime());

		final GroupWindowedTable window = table
				.where($("age").isGreater(20))
				.window(Tumble.over(lit(5).second()).on($("time")).as("win"));

		final Table result = window
				.groupBy($("win"), $("id"))
				.select($("id"), $("id").count(), $("win").start(), $("win").end());

		// table 中的数据要输出必须转换成datastream

		//		final DataStream<Persion> persionDataStream = tableEnv.toAppendStream(result, Persion.class);

		/**
		 * 第二种：
		 * tableEnv.toRetractStream(result, Row.class)
		 * 这种方法在任何情况下都是正确的
		 */
		final DataStream<Tuple2<Boolean, Row>> persionDataStream = tableEnv.toRetractStream(result, Row.class)

				// 如果只想打印最新的数据，可以加一条过滤
				.filter(tuple2 -> tuple2.f0)

				;


		persionDataStream.print();

		env.execute("xxxxx");
	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Person {
		private String id;
		private String name;
		private int age;
		private long time;
	}
}
