package com.smxknife.flink.ceo.demo.ordertimeout;

import org.apache.flink.cep.PatternSelectFunction;

import java.util.List;
import java.util.Map;

/**
 * @author smxknife
 * 2020/9/1
 */
public class OrderPatternSelectFunction implements PatternSelectFunction<OrderEvent, OrderState> {
	@Override
	public OrderState select(Map<String, List<OrderEvent>> map) throws Exception {
		List<OrderEvent> createOrders = map.get("createOrder");
		List<OrderEvent> payOrders = map.get("payOrder");

		OrderEvent createOrderEvent = createOrders.iterator().next();
		OrderEvent payOrderEvent = payOrders.iterator().next();
		return new OrderState(createOrderEvent.getOrderId(), createOrderEvent.getTimestamp(), payOrderEvent.getTimestamp(), "订单完成");
	}
}
