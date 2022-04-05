package com.smxknife.flink.table.demo.demo03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.Csv;
import org.apache.flink.table.descriptors.FileSystem;
import org.apache.flink.table.descriptors.Schema;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author smxknife
 * 2020/9/9
 */
public class _10_TableFsOutput {
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
				.readTextFile(_10_TableFsOutput.class.getResource("/Sensor.csv").getPath())
				.map((input) -> {
					String[] array = input.split(",");
					return new _01_TableFromStream.Sensor(array[0], Double.valueOf(array[1]), Long.valueOf(array[2]));
				})
		);

		Table orgTable = tableEnv.from("tb_input")
				.select($("id"), $("value"));

		Table aggTable = tableEnv.from("tb_input")
				.groupBy($("id"))
				.select($("id"), $("value").sum());

		// 将结果表输出到文件
		tableEnv.connect(new FileSystem()
				.path("/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-flink/smxknife-flink-table-demo/src/main/resources/OrgSensor.csv"))
				.withFormat(new Csv())
				.withSchema(new Schema().field("id", DataTypes.STRING())
						.field("value", DataTypes.DOUBLE())
				)
				.createTemporaryTable("tb_org_output");

		tableEnv.connect(new FileSystem()
				.path("/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-flink/smxknife-flink-table-demo/src/main/resources/AggSensor.csv"))
				.withFormat(new Csv())
				.withSchema(new Schema().field("id", DataTypes.STRING())
						.field("sumVal", DataTypes.DOUBLE())
				)
				.createTemporaryTable("tb_agg_output");

		// 没有聚合的情况
		orgTable.executeInsert("tb_org_output");

		// 有聚合情况
		// 这里将报错，不支持修改
		// aggTable.executeInsert("tb_agg_output");

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
