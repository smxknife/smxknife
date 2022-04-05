package com.smxknife.flink.table.demo.demo03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author smxknife
 * 2020/9/9
 */
public class _01_TableFromStream {
	public static void main(String[] args) {

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		SingleOutputStreamOperator<Sensor> sensorDataStream = env.readTextFile(_01_TableFromStream.class.getResource("/Sensor.csv").getPath())
				.map((String input) -> {
					String[] array = input.split(",");
					return new Sensor(array[0], Double.valueOf(array[1]), Long.valueOf(array[2]));
				});

		// 1. 基于env创建表环境
		EnvironmentSettings fsSettings = EnvironmentSettings.newInstance().useOldPlanner().inStreamingMode().build();
		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, fsSettings);

		// 2. 基于tableEnv，将流转换成表
		Table dataTable = tableEnv.fromDataStream(sensorDataStream);

		// 3. 调用table api
		Table table = dataTable.select($("id"), $("value"), $("timestamp"))
				.filter($("id").isEqual("2"));

		TableResult tableResult = table.execute();
		tableResult.print();

//		// 4. 将table转换为流
//		DataStream<Sensor> appendStream = tableEnv.toAppendStream(table, Sensor.class);
//		appendStream.print();
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
