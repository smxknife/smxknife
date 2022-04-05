package com.smxknife.cloud.netflix.service;

/**
 * @author smxknife
 * 2021/5/31
 */
public interface BizService {
	/**
	 * 采购
	 */
	void purchase(String userId, String commodityCode, int orderCount);
}
