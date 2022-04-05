package com.smxknife.flink.table.demo.demo03;

import org.apache.flink.table.annotation.DataTypeHint;
import org.apache.flink.table.annotation.FunctionHint;
import org.apache.flink.table.functions.TableFunction;
import org.apache.flink.types.Row;

@FunctionHint(output = @DataTypeHint("Row<su1 STRING, su2 STRING>"))
public class SensorTableFunction extends TableFunction<Row> {
	public void eval(String id) {
		collect(Row.of(id + "_suffix_1", id + "_suffix_2"));
	}
}