package com.smxknife.flink.table.demo.demo03;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.FileSystem;
import org.apache.flink.table.descriptors.OldCsv;
import org.apache.flink.table.descriptors.Schema;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author smxknife
 * 2020/9/9
 */
public class _05_TableCreateTable {
	public static void main(String[] args) throws Exception {
		// 这里创建表，意思是在Catalog里面创建一个表
		// 只有在createTemporaryTable()方法，才可以进行注册
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// 1. 创建Table执行环境
		EnvironmentSettings settings = EnvironmentSettings.newInstance()
				.useOldPlanner()    // 老版本的planner
				.inStreamingMode()  // 流处理模式
				.build();
		// 默认这里创建的是老版本的流式table Environment
		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

		// 2. 创建一张表，用于读取数据，从文件系统里读取
		tableEnv.connect(new FileSystem().path(_05_TableCreateTable.class.getResource("/Sensor.csv").getPath()))
				.withFormat(new OldCsv()) // 定义从外部文件读取数据之后格式化方法
				.withSchema(new Schema() // 定义表结构
						.field("id", DataTypes.STRING())
						.field("value", DataTypes.DOUBLE())
						.field("timestamp", DataTypes.BIGINT())
				)
				.createTemporaryTable("tb_input"); // 在catalog里面注册一张表

		// 4.1 通过table api查询算子，得到一张结果表
		Table table1 = tableEnv.from("tb_input")
				.select($("id"), $("value"), $("timestamp"))
				.filter($("id").isEqual("2"));

		Table table2 = tableEnv.from("tb_input")
				.select($("id"), $("value"), $("timestamp"));

		table1.execute().print();
		table2.execute().print();

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
