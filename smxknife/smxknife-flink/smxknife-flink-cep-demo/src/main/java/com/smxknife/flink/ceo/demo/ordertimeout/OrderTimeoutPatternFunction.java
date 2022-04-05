package com.smxknife.flink.ceo.demo.ordertimeout;

import org.apache.flink.cep.PatternTimeoutFunction;

import java.util.List;
import java.util.Map;

/**
 * @author smxknife
 * 2020/9/1
 */
public class OrderTimeoutPatternFunction implements PatternTimeoutFunction<OrderEvent, OrderState> {
	@Override
	public OrderState timeout(Map<String, List<OrderEvent>> map, long timestamp) throws Exception {
		// System.out.println("timestamp = " + timestamp);
		List<OrderEvent> createOrders = map.get("createOrder");
		OrderEvent createOrderEvent = createOrders.iterator().next();
		// System.out.println("timeout : create " + createOrderEvent);

		List<OrderEvent> payOrders = map.get("payOrder");
		// 这里拿到的是null，不是空集合
//		if (payOrders == null) {
//			System.out.println("******* payOrder is null not empty");
//		}
		return new OrderState(createOrderEvent.getOrderId(), createOrderEvent.getTimestamp(), null, "订单超时失效");
	}
}
