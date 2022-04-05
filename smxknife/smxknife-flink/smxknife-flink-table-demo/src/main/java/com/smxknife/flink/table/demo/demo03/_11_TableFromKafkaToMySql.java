package com.smxknife.flink.table.demo.demo03;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.DataTypes;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.Csv;
import org.apache.flink.table.descriptors.Kafka;
import org.apache.flink.table.descriptors.Schema;
import org.apache.flink.types.Row;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author smxknife
 * 2020/9/14
 */
public class _11_TableFromKafkaToMySql {
	public static void main(String[] args) throws Exception {

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);

		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

		tableEnv.connect(new Kafka().version("0.10").topic("sensor")
				.property("zookeeper.connect", "localhost:2181/kafka2.60")
				.property("bootstrap.servers", "localhost:9092"))
				.withFormat(new Csv())
				.withSchema(new Schema().field("id", DataTypes.STRING())
						.field("value", DataTypes.DOUBLE())
						.field("timestamp", DataTypes.BIGINT()))
				.createTemporaryTable("kfInput");

		Table table = tableEnv.from("kfInput")
				.groupBy($("id"))
				.select($("id"), $("value").sum().as("sumVal"));

		String mysqlDDL = "CREATE TABLE sensorAgg (" +
				"id varchar(10) not null, " +
				"sumVal double not null," +
				"PRIMARY KEY (id) NOT ENFORCED) " +
				"WITH (" +
				"'connector' = 'jdbc'," +
				"'url' = 'jdbc:mysql://localhost:3306/sensor_agg'," +
				"'table-name' = 'sensor_sum'," +
				"'driver' = 'com.mysql.cj.jdbc.Driver'," +
				"'username' = 'root'," +
				"'password' = 'root'," +
				"'sink.buffer-flush.max-rows' = '0')";
		tableEnv.executeSql(mysqlDDL);

		tableEnv.toRetractStream(table, Row.class).print();

		table.executeInsert("sensorAgg");

		env.execute("xxx");
	}
}
