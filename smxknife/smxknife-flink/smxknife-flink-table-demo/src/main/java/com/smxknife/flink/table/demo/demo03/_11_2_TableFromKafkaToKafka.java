package com.smxknife.flink.table.demo.demo03;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.Csv;
import org.apache.flink.table.descriptors.Kafka;
import org.apache.flink.table.descriptors.Schema;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author smxknife
 * 2020/9/14
 */
public class _11_2_TableFromKafkaToKafka {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);

		EnvironmentSettings settings = EnvironmentSettings.newInstance().useBlinkPlanner().build();
		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

		tableEnv.connect(new Kafka().version("0.10").topic("sensor")
				.property("zookeeper.connect", "localhost:2181/kafka2.60")
				.property("bootstrap.servers", "localhost:9092"))
				.withFormat(new Csv())
				.withSchema(new Schema().field("id", DataTypes.STRING())
				.field("value", DataTypes.DOUBLE())
				.field("timestamp", DataTypes.BIGINT()))
				.createTemporaryTable("kafkaSensorInput");

		Table filterTable = tableEnv.from("kafkaSensorInput")
				.select($("id"), $("value"))
				.filter($("id").isEqual("2"));

		Table aggTable = tableEnv.from("kafkaSensorInput")
				.groupBy($("id"))
				.select($("id"), $("value").sum());

		tableEnv.connect(new Kafka().version("0.10").topic("sensor-agg")
				.property("zookeeper.connect", "localhost:2181/kafka2.60")
				.property("bootstrap.servers", "localhost:9092"))
				.withFormat(new Csv())
				.withSchema(new Schema().field("id", DataTypes.STRING())
						.field("value", DataTypes.DOUBLE()))
				.createTemporaryTable("kafkaSensorOutput");

		//tableEnv.toRetractStream(aggTable, Row.class).print();
		// kafka同样不支持聚合操作
		// aggTable.executeInsert("kafkaSensorOutput");
		filterTable.executeInsert("kafkaSensorOutput");

		//env.execute("xxx");

	}
}
