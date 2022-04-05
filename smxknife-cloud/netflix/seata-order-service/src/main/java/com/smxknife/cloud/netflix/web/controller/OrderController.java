package com.smxknife.cloud.netflix.web.controller;

import com.smxknife.cloud.netflix.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/5/31
 */
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("create")
	public String create(String userId, String commodityCode, int orderCount) {
		orderService.create(userId, commodityCode, orderCount);
		return String.format("userId: %s, commodityCode: %s, orderCount: %s create success", userId, commodityCode, orderCount);
	}
}
