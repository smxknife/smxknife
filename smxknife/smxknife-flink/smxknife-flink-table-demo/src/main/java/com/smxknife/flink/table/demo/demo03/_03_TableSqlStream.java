package com.smxknife.flink.table.demo.demo03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

/**
 * @author smxknife
 * 2020/9/9
 */
public class _03_TableSqlStream {
	public static void main(String[] args) throws Exception {

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		SingleOutputStreamOperator<Sensor> sensorDataStream = env.readTextFile(_03_TableSqlStream.class.getResource("/Sensor.csv").getPath())
				.map((String input) -> {
					String[] array = input.split(",");
					return new Sensor(array[0], Double.valueOf(array[1]), Long.valueOf(array[2]));
				});

		// 1. 基于env创建表环境
		EnvironmentSettings fsSettings = EnvironmentSettings.newInstance().useOldPlanner().inStreamingMode().build();
		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, fsSettings);

		// 2. 基于tableEnv，将流转换成表
		Table dataTable = tableEnv.fromDataStream(sensorDataStream);

		// 3. 注册表，旧方法使用registerTable，现在已经被启用了，可以使用createTemporaryView
		tableEnv.createTemporaryView("sensor_table", dataTable);

		// 4. sql语句查询
		Table sqlQueryTable = tableEnv.sqlQuery("select id, `value`, `timestamp` from sensor_table where id = '2'");

		// 5. sql 查询打印结果
		sqlQueryTable.execute().print();
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
