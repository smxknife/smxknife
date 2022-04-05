package com.smxknife.flink.demo._transformation;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2021/6/3
 */
public class ProcessOperator {
	public static void main(String[] args) throws Exception {

		StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();
		executionEnvironment.setParallelism(1);
		final DataStreamSource<String> stream = executionEnvironment.fromElements("皖PN9999 120", "浙A11111 130", "浙A22222 80", "浙A33333 100");

		stream.map(new MapFunction<String, Tuple2<String, Integer>>() {
			@Override
			public Tuple2<String, Integer> map(String s) throws Exception {
				final String[] info = s.split(" ");
				final String carId = info[0];
				final Integer speed = Integer.valueOf(info[1]);
				return Tuple2.of(carId, speed);
			}
		}).keyBy(new KeySelector<Tuple2<String, Integer>, String>() {
			@Override
			public String getKey(Tuple2<String, Integer> tuple2) throws Exception {
				return tuple2.f0;
			}
		}).process(new KeyedProcessFunction<String, Tuple2<String, Integer>, String>() {
			@Override
			public void processElement(Tuple2<String, Integer> tuple2, Context context, Collector<String> collector) throws Exception {
				if (tuple2.f1 > 120) {
					final long processingTime = context.timerService().currentProcessingTime();
					final long now = System.currentTimeMillis();
					final long ptime = processingTime + 2 * 1000;
					System.out.println("----- processingTime: " + processingTime + " | now: " + now + " | ptime: " + ptime);
					context.timerService().registerProcessingTimeTimer(ptime);
					collector.collect(tuple2.f0);
				}
			}

			@Override
			public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) throws Exception {
//				System.out.println("***** ontimer: " + timestamp);
				out.collect("Warning: time: " + timestamp + " carId: " + ctx.getCurrentKey() + " 速度超过110");
			}
		}).print();


		executionEnvironment.execute();

		TimeUnit.SECONDS.sleep(10);

	}
}
