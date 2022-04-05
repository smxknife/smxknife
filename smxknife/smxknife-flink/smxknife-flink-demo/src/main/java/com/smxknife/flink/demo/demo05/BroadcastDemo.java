package com.smxknife.flink.demo.demo05;

import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.util.Collector;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author smxknife
 * 2020/9/8
 */
public class BroadcastDemo {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);

		// 1. 定义MapStateDescriptor
		MapStateDescriptor<String, String> itemScript = new MapStateDescriptor<>("itemScript", BasicTypeInfo.STRING_TYPE_INFO, BasicTypeInfo.STRING_TYPE_INFO);

		// 2. 数据源
		DataStreamSource<String> dataStreamSource = env.addSource(new SourceFunction<String>() {

			private volatile boolean isRun = true;
			private final AtomicLong number = new AtomicLong(0);

			@Override
			public void run(SourceContext<String> sourceContext) throws Exception {
				while (isRun) {
					sourceContext.collect("signal_" + number.getAndIncrement());
					TimeUnit.SECONDS.sleep(1);
				}
			}

			@Override
			public void cancel() {
				isRun = false;
			}
		});

		// 3. 数据源注册成广播流
		BroadcastStream<String> broadcastStream = dataStreamSource.broadcast(itemScript);

		// 4. 连接广播流和处理数据的流
		DataStreamSource<String> dataStream = env.socketTextStream("localhost", 9999);
		dataStream.connect(broadcastStream)
				.process(new BroadcastProcessFunction<String, String, Tuple2<String, String>>() {
					@Override
					public void processElement(String input, ReadOnlyContext readOnlyContext, Collector<Tuple2<String, String>> collector) throws Exception {
						ReadOnlyBroadcastState<String, String> broadcastState = readOnlyContext.getBroadcastState(itemScript);
						StringBuilder builder = new StringBuilder("--- 接收数据 input = ").append(input).append(" | config = \r\n");
						Iterator<Map.Entry<String, String>> iterator = broadcastState.immutableEntries().iterator();
						while (iterator.hasNext()) {
							Map.Entry<String, String> next = iterator.next();
							builder.append("    -").append(next.getKey()).append(" = ").append(next.getValue()).append("\r\n");
						}
						System.out.println(builder.toString());
					}

					@Override
					public void processBroadcastElement(String broadcast, Context context, Collector<Tuple2<String, String>> collector) throws Exception {
						System.out.println("---- 接收广播 broadcast = " + broadcast);
						BroadcastState<String, String> broadcastState = context.getBroadcastState(itemScript);
						broadcastState.put("itemScript", broadcast);
					}
				}).print("xxx");

		env.execute("broadcast job");
	}
}
