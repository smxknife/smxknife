package com.smxknife.flink.demo._conntable;

import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.java.io.TextInputFormat;
import org.apache.flink.core.fs.Path;
import org.apache.flink.streaming.api.datastream.BroadcastStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;
import org.apache.flink.util.Collector;

/**
 *
 * @author smxknife
 * 2021/6/8
 */
public class BroadcastDemo {
	public static void main(String[] args) throws Exception {
		// 维度表实战
		/**
		 * 假如维度信息放在文件中存储，实际可以是kafka
		 * 数据流每次数据过来，都需要从维度表中获取数据
		 */

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		String filepath = "file:///Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-flink/smxknife-flink-demo/src/main/resources/region";
		final DataStreamSource<String> fileSource = env.readFile(new TextInputFormat(new Path(filepath)), filepath, FileProcessingMode.PROCESS_CONTINUOUSLY, 10);

		/**
		 * 数据流就是单纯的数字
		 */
		final DataStreamSource<String> socketTextStream = env.socketTextStream("localhost", 8888);

		final MapStateDescriptor<String, String> configDescriptor = new MapStateDescriptor<>("config", BasicTypeInfo.STRING_TYPE_INFO, BasicTypeInfo.STRING_TYPE_INFO);

		final BroadcastStream<String> broadcast = fileSource.broadcast(configDescriptor);

		final SingleOutputStreamOperator<String> process = socketTextStream.connect(broadcast)
				.process(new BroadcastProcessFunction<String, String, String>() {
					@Override
					public void processElement(String element, ReadOnlyContext readOnlyContext, Collector<String> collector) throws Exception {
						final ReadOnlyBroadcastState<String, String> broadcastState = readOnlyContext.getBroadcastState(configDescriptor);
						final String configValue = broadcastState.get(element);
						if (configValue == null) {
							collector.collect(element + "| not config data");
						} else {
							collector.collect(configValue);
						}
					}

					// 配置流中的信息写到广播中
					@Override
					public void processBroadcastElement(String config, Context context, Collector<String> collector) throws Exception {
						final BroadcastState<String, String> broadcastState = context.getBroadcastState(configDescriptor);
						final String[] configs = config.split(" ");
						broadcastState.put(configs[0], configs[1]);
					}
				});

		process.print();

		env.execute();
	}
}
