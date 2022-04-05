package com.smxknife.flink.table.demo.demo02;

import com.smxknife.flink.table.demo.demo01.EnergyData;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

import static org.apache.flink.table.api.Expressions.$;

/**
 * @author smxknife
 * 2020/9/2
 */
public class Main {
	public static void main(String[] args) {

		// create a TableEnvironment for specific planner batch or streaming
		EnvironmentSettings fsSettings = EnvironmentSettings.newInstance().useOldPlanner().inStreamingMode().build();
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, fsSettings);

		SingleOutputStreamOperator<EnergyData> energyDataStream = env.readTextFile(Main.class.getResource("/Industry.csv").getPath())
				.map(new MapFunction<String, EnergyData>() {
					@Override
					public EnergyData map(String data) throws Exception {
						String[] array = data.split(",");
						return new EnergyData(array[0].trim(), array[1].trim(), array[2].trim(),
								array[3].trim(), Double.valueOf(array[4].trim()), Long.valueOf(array[5].trim()));
					}
				});
		// create a Table
		Table table = tableEnv.fromDataStream(energyDataStream,
				$("dataCode"), $("usage"), $("energyClassCode"),
				$("energyTypeCode"), $("value"), $("timestamp"));

		tableEnv.createTemporaryView("energyData", table);

		Table energyDataTable = tableEnv.from("energyData").select($("dataCode"), $("energyClassCode"),
				$("energyTypeCode"), $("value"), $("timestamp"))
				.filter($("energyTypeCode").isEqual("3303"));

		TableResult tableResult = energyDataTable.execute();

		tableResult.print();

	}
}
