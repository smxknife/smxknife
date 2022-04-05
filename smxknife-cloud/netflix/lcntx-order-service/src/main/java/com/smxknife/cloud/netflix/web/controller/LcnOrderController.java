package com.smxknife.cloud.netflix.web.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.smxknife.cloud.netflix.entity.Order;
import com.smxknife.cloud.netflix.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author smxknife
 * 2021/5/30
 */
@RestController
@RequestMapping("/order/lcn")
public class LcnOrderController {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("create")
	@Transactional(rollbackFor = Exception.class)
	@LcnTransaction
	public String createOrder(@RequestBody Order orderEvent) {

		repository.save(orderEvent);
		restTemplate.postForObject("http://lcntx-pay-service/pay/lcn/create", orderEvent, String.class);

		return "订单创建成功";
	}
}
