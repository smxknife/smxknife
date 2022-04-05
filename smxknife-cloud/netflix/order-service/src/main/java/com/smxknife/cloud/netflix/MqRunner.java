package com.smxknife.cloud.netflix;

import com.alibaba.fastjson.JSONObject;
import com.smxknife.cloud.netflix.entity.OrderEvent;
import com.smxknife.cloud.netflix.service.OrderEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2021/5/29
 */
@Component
public class MqRunner implements CommandLineRunner {

	@Autowired
	private OrderEventService orderEventService;

	@Override
	public void run(String... args) throws Exception {

	}

	@KafkaListener(topics = "order-events")
	public void receiveEvents(String events) {
		final JSONObject jsonObject = JSONObject.parseObject(events);
		System.out.println("receive events: " + jsonObject);
		final OrderEvent orderEvent = JSONObject.toJavaObject(jsonObject, OrderEvent.class);
		orderEventService.receiveEvent(orderEvent);
	}
}
