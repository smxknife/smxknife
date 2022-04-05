package com.smxknife.flink.useranalysis.hotitemsanalysis;

import com.smxknife.flink.useranalysis.hotitemsanalysis.model.ItemViewCount;
import com.smxknife.flink.useranalysis.hotitemsanalysis.model.UserBehavior;
import org.apache.flink.api.common.eventtime.*;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

/**
 * @author smxknife
 * 2020/8/22
 */
public class HotItems {
	public static void main(String[] args) throws Exception {
		// 1. 创建执行环境
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		// 设置并行度
		env.setParallelism(1);
		// 设置时间语义
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

		// 2. 读取数据
		DataStreamSource<String> streamSource = env.readTextFile("file:///Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-flink/smxknife-flink-useranalysis/smxknife-flink-useranalysis-com.smxknife.flink.useranalysis.hotitemsanalysis/src/main/resources/UserBehavior.csv");

		DataStream<UserBehavior> dataStream = streamSource.map((data) -> {
			String[] dataArray = data.split(",");
			return new UserBehavior(Long.valueOf(dataArray[0].trim()), Long.valueOf(dataArray[1].trim()), Integer.valueOf(dataArray[2].trim()), dataArray[3], Long.valueOf(dataArray[4].trim()));
		})
//				.assignTimestampsAndWatermarks(WatermarkStrategy.<UserBehavior>forBoundedOutOfOrderness(Duration.ofSeconds(3)).withTimestampAssigner((event, t) -> event.getTimestamp() * 1000));

				.assignTimestampsAndWatermarks(new WatermarkStrategy<UserBehavior>() {
					@Override
					public WatermarkGenerator<UserBehavior> createWatermarkGenerator(WatermarkGeneratorSupplier.Context context) {
						return new WatermarkGenerator<UserBehavior>() {
							private long maxTimestamp = Long.MIN_VALUE;
							// 每来一条数据，将这条数据与maxTimesStamp比较，看是否需要更新watermark
							@Override
							public void onEvent(UserBehavior userBehavior, long eventTimestamp, WatermarkOutput watermarkOutput) {
								maxTimestamp = Math.max(maxTimestamp, eventTimestamp);
							}

							// 周期性更新watermark，这里触发窗口关闭
							@Override
							public void onPeriodicEmit(WatermarkOutput watermarkOutput) {
								// 如果是乱序，这里需要设置延迟，如果设置为0，表示不延迟
								long maxOutOfOrderness = 0;
								watermarkOutput.emitWatermark(new Watermark(maxTimestamp - maxOutOfOrderness));
							}
						};
					}
				}.withTimestampAssigner((element, recordingTimestamp) -> element.getTimestamp() * 1000));

//				.assignTimestampsAndWatermarks(WatermarkStrategy.<UserBehavior>forMonotonousTimestamps().withTimestampAssigner(new SerializableTimestampAssigner<UserBehavior>() {
//					@Override
//					public long extractTimestamp(UserBehavior userBehavior, long l) {
//						return userBehavior.getTimestamp() * 1000;
//					}
//				}));

		// 3. transform处理数据
		//  -- 认为pv是热门数据
		DataStream<String> processStream = dataStream
				.filter(ub -> "pv".equals(ub.getBehavior()))
				.keyBy(UserBehavior::getItemId)
				.timeWindow(Time.hours(1), Time.minutes(30))
				.aggregate(new CountAgg(), new WindowResult()) // 窗口聚合
				.keyBy(ItemViewCount::getWindowEnd) // 按照窗口分组
				.process(new TopNHotItems(10));

		// 4. 控制台输出
		processStream.print();

		env.execute("hot items job");

	}
}
