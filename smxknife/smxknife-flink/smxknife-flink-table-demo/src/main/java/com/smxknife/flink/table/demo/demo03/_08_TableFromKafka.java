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
import org.apache.flink.table.descriptors.Kafka;
import org.apache.flink.table.descriptors.Schema;

/**
 * @author smxknife
 * 2020/9/9
 */
public class _08_TableFromKafka {
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

		tableEnv.connect(new Kafka()
				.version("2.6.0") // kafka的版本
				.topic("sensor")  // 定义主题
				.property("zookeeper.connect", "localhost:2181/kafka2.60")
				.property("bootstrap.servers", "localhost:9092"))
					.withFormat(new Csv())
					.withSchema(new Schema()
							.field("id", DataTypes.STRING())
							.field("value", DataTypes.DOUBLE())
							.field("timestamp", DataTypes.BIGINT())
					)
				.createTemporaryTable("tb_input");

		Table table = tableEnv.sqlQuery("select id, sum(`value`) as val from tb_input group by id");

		table.execute().print();

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
