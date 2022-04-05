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

/**
 * @author smxknife
 * 2021/5/30
 */
@RestController
@RequestMapping("/pay/lcn")
public class LcnPayController {

	@Autowired
	private OrderRepository repository;

	@PostMapping("create")
	@Transactional(rollbackFor = Exception.class)
	@LcnTransaction
	public String create(@RequestBody Order orderEvent) {
		repository.save(orderEvent);
		return "pay新增成功";
	}
}
