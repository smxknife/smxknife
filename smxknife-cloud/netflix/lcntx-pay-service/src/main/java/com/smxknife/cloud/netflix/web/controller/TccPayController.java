package com.smxknife.cloud.netflix.web.controller;

import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.smxknife.cloud.netflix.entity.Order;
import com.smxknife.cloud.netflix.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author smxknife
 * 2021/5/30
 */
@RestController
@RequestMapping("/pay/tcc")
public class TccPayController {

	@Autowired
	private OrderRepository repository;

	@PostMapping("create")
	@Transactional(rollbackFor = Exception.class)
	@TccTransaction(confirmMethod = "confirmCreate", cancelMethod = "cancelCreate")
	public String create(@RequestBody Order orderEvent) {
		repository.save(orderEvent);
		return "pay新增成功";
	}

	public String confirmCreate(Order order) {
		System.out.println("----- confirm create order : " + order);
		return "confirm 电子支付创建成功";
	}

	public String cancelCreate(Order order) {
		System.out.println("----- cancel create order : " + order);
		return "cancel 电子支付创建失败";
	}
}
