package com.smxknife.flink.useranalysis.hotitemsanalysis;

import com.smxknife.flink.useranalysis.hotitemsanalysis.model.UserBehavior;
import org.apache.flink.api.common.functions.AggregateFunction;

/**
 * 自定义预聚合函数
 * @author smxknife
 * 2020/8/22
 */
public class CountAgg implements AggregateFunction<UserBehavior, Long, Long> {
	// 创建累加器，这里就是0
	@Override
	public Long createAccumulator() {
		return 0L;
	}

	@Override
	public Long add(UserBehavior userBehavior, Long accumulator) {
		// System.out.println("CountAgg | " + userBehavior + " | accumulator = " + accumulator);
		// System.out.println("ItemId = " + userBehavior.getItemId() + " | accumulator = " + accumulator);
		return accumulator + 1;
	}

	@Override
	public Long getResult(Long accumulator) {
		// System.out.println("getResult : " + accumulator);
		return accumulator;
	}

	@Override
	public Long merge(Long acc1, Long acc2) {
		return acc1 + acc2;
	}
}
