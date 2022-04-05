package com.smxknife.flink.table.demo.demo01;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.types.Row;

import static org.apache.flink.table.api.Expressions.$;

/**
 * 这个例子是比较随便凑的，所以存在很多漏洞，看TODO部分
 * @author smxknife
 * 2020/9/1
 */
public class Main {
	public static void main(String[] args) throws Exception {

		EnvironmentSettings fsSettings = EnvironmentSettings.newInstance().useOldPlanner().inStreamingMode().build();
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		SingleOutputStreamOperator<EnergyData> energyDataStream = env.socketTextStream("localhost", 9999)
				.map(new MapFunction<String, EnergyData>() {
					@Override
					public EnergyData map(String data) throws Exception {
						System.out.println("--- data" + data);
						String[] array = data.split(",");
						return new EnergyData(array[0].trim(), array[1].trim(), array[2].trim(),
								array[3].trim(), Double.valueOf(array[4].trim()), Long.valueOf(array[5].trim()));
					}
				});

		// 基于env创建表环境
		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, fsSettings);

		// 基于tableEnv，将流转换为表
		Table table = tableEnv.fromDataStream(energyDataStream,
				$("dataCode"), $("usage"), $("energyClassCode"),
				$("energyTypeCode"), $("value"), $("timestamp"));

		// 这种方式与上面那种方式类似，只不过，这种方式的属性属性由属性名的顺序决定的
		// table = tableEnv.fromDataStream(energyDataStream);

		tableEnv.createTemporaryView("energyData", table);

		// 调用table api 做转换操作
		Table energyTable = tableEnv.from("energyData")
				.filter($("energyTypeCode").isEqual("3303"))
				.select($("dataCode"), $("usage"), $("energyClassCode"),
						$("energyTypeCode"), $("value"), $("timestamp"));
		// TableResult energyTypeCodeTable = energyTable.execute();

		DataStream rowDataStream = tableEnv.toAppendStream(energyTable, Row.class);
		rowDataStream.print();
		env.execute("table api job");
	}
}
