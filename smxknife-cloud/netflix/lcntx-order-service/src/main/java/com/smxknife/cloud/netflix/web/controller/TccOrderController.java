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
import org.springframework.web.client.RestTemplate;

/**
 * @author smxknife
 * 2021/5/30
 */
@RestController
@RequestMapping("/order/tcc")
public class TccOrderController {

	@Autowired
	private OrderRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("create")
	@Transactional(rollbackFor = Exception.class)
	@TccTransaction(confirmMethod = "confirmCreate", cancelMethod = "cancelCreate")
	public String createOrder(@RequestBody Order orderEvent) {

		repository.save(orderEvent);
		restTemplate.postForObject("http://lcntx-pay-service/pay/tcc/create", orderEvent, String.class);
		return "订单创建成功";
	}

	/**
	 * 这里方法名如果是confirm开头，可以无需修改@TccTransaction，否则，需要在注解的confirmMethod参数中添加
	 * 下面的cancelCreate同理
	 * ----------------------------
	 * 但是：实际测试，如果不写，没法执行
	 * @param order
	 * @return
	 */
	public String confirmCreate(Order order) {
		System.out.println("----- confirm create order: " + order);
		return "confirm 订单创建成功";
	}

	public String cancelCreate(Order order) {
		System.out.println("----- cancel create order: " + order);

		return "cancel 订单创建失败";
	}
}
