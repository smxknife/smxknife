package com.smxknife.cloud.netflix.service;

/**
 * @author smxknife
 * 2021/5/31
 */
public interface AccountService {
	/**
	 * 从用户账户中借出
	 */
	void debit(String userId, long money);
}
