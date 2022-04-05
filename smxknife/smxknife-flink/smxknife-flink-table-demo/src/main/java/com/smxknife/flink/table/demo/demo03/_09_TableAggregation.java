package com.smxknife.flink.table.demo.demo03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author smxknife
 * 2020/9/9
 */
public class _09_TableAggregation {
	public static void main(String[] args) throws Exception {
		// 这里创建表，意思是在Catalog里面创建一个表
		// 只有在createTemporaryTable()方法，才可以进行注册
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);

		// 1. 创建Table执行环境
		EnvironmentSettings settings = EnvironmentSettings.newInstance()
				.useOldPlanner()    // 老版本的planner
				.inStreamingMode()  // 流处理模式
				.build();
		// 默认这里创建的是老版本的流式table Environment
		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

		tableEnv.createTemporaryView("tb_input", env
				.readTextFile(_09_TableAggregation.class.getResource("/Sensor.csv").getPath())
				.map((input) -> {
					String[] array = input.split(",");
					return new _01_TableFromStream.Sensor(array[0], Double.valueOf(array[1]), Long.valueOf(array[2]));
				})
		);

		Table table = tableEnv.from("tb_input")
				.groupBy($("id"))
				.select($("id"), $("value").sum());

		tableEnv.toRetractStream(table, Row.class).print();
		env.execute("xxxx");
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
