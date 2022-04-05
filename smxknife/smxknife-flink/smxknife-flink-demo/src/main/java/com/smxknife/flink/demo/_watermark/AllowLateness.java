package com.smxknife.flink.demo._watermark;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.ProcessAllWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * @author smxknife
 * 2021/6/7
 */
public class AllowLateness {
	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		env.setParallelism(1);

		final DataStreamSource<String> dataStreamSource = env.socketTextStream("localhost", 8888);
		final OutputTag<Tuple2<Long, String>> lateTag = new OutputTag<Tuple2<Long, String>>("late"){};

		final SingleOutputStreamOperator<Tuple2<Long, String>> process = dataStreamSource.map(new MapFunction<String, Tuple2<Long, String>>() {
			@Override
			public Tuple2<Long, String> map(String s) throws Exception {
				final String[] strings = s.split(" ");
				return Tuple2.of(Long.valueOf(strings[0]), strings[1]);
			}
		}).assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<Tuple2<Long, String>>(Time.seconds(2)) {
			@Override
			public long extractTimestamp(Tuple2<Long, String> tuple2) {
				return tuple2.f0;
			}
		}).timeWindowAll(Time.seconds(5))
				.allowedLateness(Time.seconds(3))
				.sideOutputLateData(lateTag)
				.process(new ProcessAllWindowFunction<Tuple2<Long, String>, Tuple2<Long, String>, TimeWindow>() {
					@Override
					public void process(Context context, Iterable<Tuple2<Long, String>> iterable, Collector<Tuple2<Long, String>> collector) throws Exception {
						System.out.println(context.window().getStart() + " --- " + context.window().getEnd());
						iterable.forEach(tuple2 -> collector.collect(tuple2));
					}
				});

		process.print("main");
		process.getSideOutput(lateTag).print("late");

		env.execute();
	}
}
