package com.smxknife.flink.demo._udf;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.annotation.DataTypeHint;
import org.apache.flink.table.annotation.FunctionHint;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.types.Row;

import static org.apache.flink.table.api.Expressions.$;
import static org.apache.flink.table.api.Expressions.call;

/**
 * @author smxknife
 * 2021/6/8
 */
public class TableUdfFunction {
	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		final EnvironmentSettings settings = EnvironmentSettings
				.newInstance()
				.useBlinkPlanner()
				.build();
		final StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, settings);

		final DataStreamSource<String> socketTextStream = env.socketTextStream("localhost", 8888);

		final Table table = tableEnv.fromDataStream(socketTextStream, $("line"));

		// 创建一个udf
		final MyTableFunction2 myTableFunction =
				new MyTableFunction2();

		final Table line = table
				.joinLateral(call(myTableFunction, $("line")))
				.select($("s1"), $("s2"));
		line.execute().print();
		//tableEnv.toRetractStream(line, Row.class).print();

		env.execute();

	}



	@FunctionHint(output = @DataTypeHint("Row<s1 STRING, s2 STRING>"))
	public static class MyTableFunction1 extends TableFunction<Row> {
		public void eval(String id) {
			collect(Row.of(id + "_suffix_1", id + "_suffix_2"));
		}
	}


	@FunctionHint(output = @DataTypeHint("Row<s1 STRING, s2 STRING>"))
	public static class MyTableFunction2 extends TableFunction<Row> {
		public void eval(String id) {
			collect(Row.of(id + "_suffix_1", id + "_suffix_2"));
		}

		@Override
		public TypeInformation<Row> getResultType() {
			return Types.ROW(Types.STRING, Types.STRING);
		}


	}
}
