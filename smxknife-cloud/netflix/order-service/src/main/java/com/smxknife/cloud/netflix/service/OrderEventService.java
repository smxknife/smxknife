package com.smxknife.cloud.netflix.service;

import com.smxknife.cloud.netflix.entity.OrderEvent;

import javax.transaction.Transactional;

/**
 * @author smxknife
 * 2021/5/29
 */
public interface OrderEventService {

	@Transactional
	void receiveEvent(OrderEvent orderEvent);
}
