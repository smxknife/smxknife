package com.smxknife.springboot.ebean.web.controller;

import com.smxknife.springboot.ebean.domain.Order;
import io.ebean.Ebean;
import io.ebean.Query;
import io.ebean.RawSql;
import io.ebean.RawSqlBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author smxknife
 * 2018/8/30
 */
@RestController
public class EbeanRestController {

	@RequestMapping("/")
	public String test() {
		String sql = "SELECT o.user_id, o.order_id FROM t_order0 o WHERE o.user_id=:userId AND o.order_id=:orderId";
		RawSql rawSql = RawSqlBuilder
				.parse(sql)
				.tableAliasMapping("o", "t_order0")
				.columnMapping("o.user_id", "userId")
				.columnMapping("o.order_id", "orderId")
				.create();
		Query<Order> orderQuery = Ebean.find(Order.class);
		List<Order> list = orderQuery.setRawSql(rawSql)
				.setParameter("userId", 1)
				.setParameter("orderId", 10)
				.findList();

		return list.stream()
				.map(order -> order.getUserId() + ": " + order.getOrderId())
				.collect(Collectors.joining(","));
	}
}
