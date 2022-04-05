package com.smxknife.cloud.netflix.service;

/**
 * @author smxknife
 * 2021/5/31
 */
public interface StorageService {
	/**
	 * 扣除存储数量
	 * @param commodityCode
	 * @param count
	 */
	void deduct(String commodityCode, int count);
}
