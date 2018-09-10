package com.smxknife.shardingsphere.jdbc.service.impl;

import com.smxknife.shardingsphere.jdbc.entity.Order;
import com.smxknife.shardingsphere.jdbc.entity.OrderItem;
import com.smxknife.shardingsphere.jdbc.repository.OrderItemRepository;
import com.smxknife.shardingsphere.jdbc.repository.OrderRepository;
import com.smxknife.shardingsphere.jdbc.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
	@Resource
	private OrderRepository orderRepository;

	@Resource
	private OrderItemRepository orderItemRepository;

	@Override
	public List<OrderItem> findAll() {
		List<Long> orderIds = new ArrayList<>(10);
		List<Long> orderItemIds = new ArrayList<>(10);
		System.out.println("1.Insert--------------");
		for (int i = 0; i < 10; i++) {
			Order order = new Order();
			order.setUserId(51);
			order.setStatus("INSERT_TEST");
			long orderId = orderRepository.save(order).getOrderId();
			orderIds.add(orderId);
			OrderItem item = new OrderItem();
			item.setOrderId(orderId);
			item.setUserId(51);
			orderItemIds.add(orderItemRepository.save(item).getOrderItemId());
		}
		List<OrderItem> orderItems = orderItemRepository.findAll();
		System.out.println(orderItemRepository.findAll());
		System.out.println("2.Delete--------------");
		if (orderItems.size() > 0) {
			for (Long each : orderItemIds) {
				orderItemRepository.deleteById(each);
			}
			for (Long each : orderIds) {
				orderRepository.deleteById(each);
			}
		}
		List<OrderItem> all = orderItemRepository.findAll();
		System.out.println(all);
		return all;
	}
}
