package com.smxknife.flink.demo._table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author smxknife
 * 2021/6/8
 */
public class CreateTableFromDataStream {
	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// 使用flink默认的tablestream，创建Table的上下文环境（TableEnvironment）
		// 注意这里需要指定settings，否则会报错
		final EnvironmentSettings settings = EnvironmentSettings.newInstance().useOldPlanner().build();
		final StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

		final DataStreamSource<String> socketTextStream = env.socketTextStream("localhost", 8888);
		final SingleOutputStreamOperator<Persion> personStream = socketTextStream.map(input -> {
			final String[] array = input.split(" ");
			return new Persion(array[0].trim(), array[1].trim(), Integer.valueOf(array[2].trim()));
		});

		// 默认使用属性名作为字段
//		final Table table1 = tableEnv.fromDataStream(personStream);
//		table1.printSchema();

		// 自定义属性名
		final Table table2 = tableEnv.fromDataStream(personStream,
				$("id").as("p_id"),
				$("name").as("p_name"),
				$("age").as("p_age")); //
		table2.printSchema();

		// 这里是从socket中读，是流计算，所以要execute
		// 这里如果调用tableEnv.execute，会报错
		env.execute("xxxxx");
	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	public static class Persion {
		private String id;
		private String name;
		private int age;
	}
}
