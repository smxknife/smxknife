package com.smxknife.flink.useranalysis.hotitemsanalysis.model;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * 输入数据样例类
 * @author smxknife
 * 2020/8/22
 */
@ToString
@AllArgsConstructor
public class UserBehavior {
	private Long userId;
	private Long itemId;
	private Integer categoryId;
	private String behavior;
	private Long timestamp;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
}
