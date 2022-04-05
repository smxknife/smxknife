package com.smxknife.flink.table.demo.demo03;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableResult;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.descriptors.ConnectorDescriptor;

import java.util.Map;

/**
 * @author smxknife
 * 2020/9/9
 */
public class _04_TableInAndOutStream {
	public static void main(String[] args) {

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		// 1. 创建Table执行环境
		EnvironmentSettings settings = EnvironmentSettings.newInstance()
				.useOldPlanner()    // 老版本的planner
				.inStreamingMode()  // 流处理模式
				.build();
		// 默认这里创建的是老版本的流式table Environment
		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

		// 1.1 因为老版本的不支持批流一体，所以不仅仅是将上面改为inBatchMode()就能转换为批处理模式，需要做以下转换
//		ExecutionEnvironment batchEnv = ExecutionEnvironment.getExecutionEnvironment();
//		BatchTableEnvironment batchTableEnv = BatchTableEnvironment.create(batchEnv);

		// 1.2 blink版本的流式查询
//		EnvironmentSettings bsSettings = EnvironmentSettings.newInstance()
//				.useBlinkPlanner()
//				.inStreamingMode()
//				.build();
//		StreamTableEnvironment bsStreamTableEnv = StreamTableEnvironment.create(env, bsSettings);

		// 1.3 blink版本的批处理查询
//		EnvironmentSettings bbSettings = EnvironmentSettings.newInstance()
//				.useBlinkPlanner()
//				.inBatchMode()
//				.build();
//		TableEnvironment bbTableEnv = TableEnvironment.create(bbSettings);

		// 2. 创建一张表，用于读取数据
		tableEnv.connect(new ConnectorDescriptor("inType", 1, false) {
			@Override
			protected Map<String, String> toConnectorProperties() {
				return null;
			}
		}).createTemporaryTable("tb_input");

		// 3. 创建一张表，用于把计算结果输出
		// tableEnv

		// 4.1 通过table api查询算子，得到一张结果表
		Table table = tableEnv.from("tb_input").select();
		// 4.2 同样也可以通过sql进行查询
		// Table table = tableEnv.sqlQuery("select `id`, `value`, `timestamp` from tb_input where id = '2'");

		// 5. 将结果写入到输出表中
		TableResult tableResult = table.executeInsert("tb_output");

		// 输出看看
		tableResult.print();

	}
}
