package com.smxknife.cloud.netflix.service.impl;

import com.smxknife.cloud.netflix.entity.OrderEvent;
import com.smxknife.cloud.netflix.repository.OrderEventRepository;
import com.smxknife.cloud.netflix.service.OrderEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author smxknife
 * 2021/5/29
 */
@Service
public class OrderEventServiceImpl implements OrderEventService {

	@Autowired
	private OrderEventRepository repository;

	@Override
	public void receiveEvent(OrderEvent orderEvent) {
		orderEvent.setId(null);
		repository.save(orderEvent);
		System.out.println("更新成功");
	}
}
