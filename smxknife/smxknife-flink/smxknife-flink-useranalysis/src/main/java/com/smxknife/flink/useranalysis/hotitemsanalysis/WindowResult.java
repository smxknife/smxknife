package com.smxknife.flink.useranalysis.hotitemsanalysis;

import com.smxknife.flink.useranalysis.hotitemsanalysis.model.ItemViewCount;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * 自定义窗口函数，输出ItemViewCount
 * @author smxknife
 * 2020/8/22
 */
public class WindowResult implements WindowFunction<Long, ItemViewCount, Long, TimeWindow> {
	@Override
	public void apply(Long key, TimeWindow timeWindow, Iterable<Long> input, Collector<ItemViewCount> collector) throws Exception {
		try {
			// System.out.println("key = " + key + " window = " + timeWindow.getEnd() + " val = " + input.iterator().next());
			collector.collect(new ItemViewCount(key, timeWindow.getEnd(), input.iterator().next()));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
