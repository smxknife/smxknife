package com.smxknife.drools.demo02.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smxknife
 * 2020/6/15
 */
@Getter
@Setter
public class RuleResult {
	// 参加活动商品优惠后的价格
	private double finallyMoney;
	// 参加活动的名称
	private List<String> promoteName = new ArrayList<>();
	private double moneySum;

	public void setPromoteName(String promoteName) {
		this.promoteName.add(promoteName);
	}

}
