package com.smxknife.cloud.netflix.service;

/**
 * @author smxknife
 * 2021/5/31
 */
public interface OrderService {
	/**
	 * 创建订单
	 */
	void create(String userId, String commodityCode, int orderCount);
}
