package com.smxknife.flink.demo._transformation;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * @author smxknife
 * 2021/6/3
 */
public class SideOutputOperator {
	// Split已经废弃，建议使用侧输出流
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
		final DataStreamSource<Long> stream = executionEnvironment.generateSequence(1, 100);

		OutputTag<Long> evenTag = new OutputTag<Long>("even") {};

		final SingleOutputStreamOperator<Long> processStream = stream.process(new ProcessFunction<Long, Long>() {
			@Override
			public void processElement(Long aLong, Context context, Collector<Long> collector) throws Exception {
				if (aLong % 2 == 0) {
					context.output(evenTag, aLong);
				} else {
					collector.collect(aLong);
				}
			}
		});

		final DataStream<Long> sideOutput = processStream.getSideOutput(evenTag);

		sideOutput.print("evenOutputStream");
		processStream.print("oddProcessStream");

		executionEnvironment.execute();
	}
}
