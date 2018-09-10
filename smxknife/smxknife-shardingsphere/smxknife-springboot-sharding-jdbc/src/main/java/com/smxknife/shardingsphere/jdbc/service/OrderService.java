package com.smxknife.shardingsphere.jdbc.service;

import com.smxknife.shardingsphere.jdbc.entity.OrderItem;

import java.util.List;

public interface OrderService {

	List<OrderItem> findAll();
}
