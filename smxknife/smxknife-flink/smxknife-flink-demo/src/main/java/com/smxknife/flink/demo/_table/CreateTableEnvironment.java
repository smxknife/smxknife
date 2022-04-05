package com.smxknife.flink.demo._table;

import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.sources.CsvTableSource;

/**
 * @author smxknife
 * 2021/6/8
 */
public class CreateTableEnvironment {
	public static void main(String[] args) {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// 使用flink默认的tablestream，创建Table的上下文环境（TableEnvironment）
		// 注意这里需要指定settings，否则会报错
		final EnvironmentSettings settings = EnvironmentSettings.newInstance().useOldPlanner().build();
		final StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

		// 使用blink的tablestream
//		final EnvironmentSettings settings = EnvironmentSettings.newInstance()
//				.useBlinkPlanner() // 是否使用alibaba的api
//				.inStreamingMode() // 是否用于流计算
//				.build();
//		final StreamTableEnvironment bTableEnv = StreamTableEnvironment.create(env, settings);

		// 这里是批计算，所以后面不需要加tableEnv.execute()
		final CsvTableSource csvTableSource = new CsvTableSource(CreateTableEnvironment.class.getResource("/region").getPath(),
				new String[]{"code", "name"}, new TypeInformation[]{Types.STRING, BasicTypeInfo.STRING_TYPE_INFO});

		// 注册一张表
		tableEnv.registerTableSource("tb_region", csvTableSource);

		final Table tb_region = tableEnv.scan("tb_region");
		tb_region.printSchema(); // 打印表结构


	}
}
