package com.smxknife.flink.useranalysis.hotitemsanalysis.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * 窗口聚合样例类
 * @author smxknife
 * 2020/8/22
 */
@ToString
@AllArgsConstructor
public class ItemViewCount {
	private Long itemId;
	private Long windowEnd;
	private Long count;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getWindowEnd() {
		return windowEnd;
	}

	public void setWindowEnd(Long windowEnd) {
		this.windowEnd = windowEnd;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
}
