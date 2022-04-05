package com.smxknife.cloud.netflix.service.impl;

import com.smxknife.cloud.netflix.entity.Order;
import com.smxknife.cloud.netflix.repository.OrderRepository;
import com.smxknife.cloud.netflix.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * @author smxknife
 * 2021/5/31
 */
@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OrderRepository orderRepository;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void create(String userId, String commodityCode, int orderCount) {
		long orderMoney = calculate(commodityCode, orderCount);

		final String postForObject = restTemplate.postForObject("http://seata-account-service/account/debit?userId={1}&money={2}",
				null, String.class, userId, orderMoney);
		System.out.println("------ " + postForObject);
		// accountService.debit(userId, orderMoney);

		Order order = new Order();
		order.setUserId(userId);
		order.setCommodityCode(commodityCode);
		order.setCount(orderCount);
		order.setMoney(orderMoney);
		orderRepository.save(order);
	}

	private long calculate(String commodityCode, int orderCount) {
		long money = 0;
		switch (commodityCode) {
			case "C0":
				money = 10 * orderCount;
				break;
			case "C1":
				money = 20 * orderCount;
			default:
				money = 1 * orderCount;
		}
		return money;
	}
}
