package com.smxknife.cloud.netflix.service;

import javax.transaction.Transactional;

/**
 * @author smxknife
 * 2021/5/29
 */
public interface PayOrderService {
	@Transactional
	void syncOrder();
}
