package com.smxknife.flink.useranalysis.hotitemsanalysis;

import com.smxknife.flink.useranalysis.hotitemsanalysis.model.ItemViewCount;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2020/8/22
 */
public class TopNHotItems extends KeyedProcessFunction<Long, ItemViewCount, String> {
	private int count;

	private ListState<ItemViewCount> itemState;

	public TopNHotItems(int count) {
		this.count = count;
	}

	@Override
	public void processElement(ItemViewCount itemViewCount, Context context, Collector<String> collector) throws Exception {
		// 把每条数据存入状态列表
		itemState.add(itemViewCount);
		context.timerService().registerEventTimeTimer(itemViewCount.getWindowEnd() + 1);
	}

	@Override
	public void open(Configuration parameters) throws Exception {
		itemState = getRuntimeContext().getListState(new ListStateDescriptor<ItemViewCount>("itemStat", ItemViewCount.class));
	}

	// 定时器触发时，对所有数据排序并输出结果
	@Override
	public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) throws Exception {
		// 将所有state中数据取出
		List<ItemViewCount> allItems = new ArrayList<>();
		itemState.get().forEach(allItems::add);

		// 按照count大小排序，并获取前count个
		List<ItemViewCount> topNItems = allItems.stream().sorted((o1, o2) -> {
			return o2.getCount().compareTo(o1.getCount());
//			return o1.getCount() > o2.getCount() ? -1 : 1; // 这种写法是有错误的，如果遇到两者相等的情况下，会抛出Comparison method violates its general contract!，因为不满足自反性，所以必须要处理相等的情况。另外还要考虑null的情况，如果前者为null怎么处理，后者为null怎么处理，两者都为null怎么处理
		}).limit(count).collect(Collectors.toList());

		// 清空状态
		itemState.clear();

		// 将排名结果格式化输出
		StringBuilder result = new StringBuilder();
		result.append("时间：").append(new Timestamp(timestamp - 1)).append("\n");

		Stream.iterate(0, idx -> idx + 1).limit(topNItems.size()).forEach(idx -> {
			ItemViewCount itemViewCount = topNItems.get(idx);
			result.append("No").append(idx + 1).append(" : ")
					.append(" 商品ID=").append(itemViewCount.getItemId()).append(" 浏览量=").append(itemViewCount.getCount())
					.append("\n")
					.append("------------------------------------------\n");
		});

		// 控制输出频率
		TimeUnit.SECONDS.sleep(1);
		out.collect(result.toString());
	}
}
