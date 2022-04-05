package com.smxknife.cloud.netflix.service.impl;

import com.alibaba.fastjson.JSON;
import com.smxknife.cloud.netflix.entity.OrderEvent;
import com.smxknife.cloud.netflix.repository.OrderEventRepository;
import com.smxknife.cloud.netflix.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author smxknife
 * 2021/5/29
 */
@Service
public class PayOrderServiceImpl implements PayOrderService {

	@Autowired
	private OrderEventRepository repository;

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public void syncOrder() {
		List<OrderEvent> newEvents = repository.findByStatus(OrderEvent.Status.NEW.getStatus());
		newEvents.forEach(orderEvent -> {
			final JSON json = (JSON)JSON.toJSON(orderEvent);
			final ListenableFuture<SendResult<String, String>> listenableFuture = kafkaTemplate.send("order-events", json.toJSONString());
			try {
				final SendResult<String, String> sendResult = listenableFuture.get(10, TimeUnit.SECONDS);
				System.out.println("发送成功 ｜ " + sendResult);
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				System.err.println("发送失败");
				// 这里就可以进行retry等相关操作
				throw new RuntimeException("消息发送失败");
			}
			orderEvent.setStatus(OrderEvent.Status.RECEIVED.getStatus());
			repository.save(orderEvent);
			System.out.println("更新成功");
		});

	}

	@PostConstruct
	public void init() {
		// 这里考虑分布式事务，要保证原子性，就不能采用这种回调处理的方式
		// 如果采用了这种方式，那么会出现几个问题：
		// - 1。如果消息发送成功后更新status，如果回调很慢，task执行很快，status没有那么快更新，那么会不断的从数据库里取出重复的订单
		// - 2。如果在取出订单的同时将status更新掉，那么假如mq挂了，应用不会回滚，也不知道是否已经发送成功
		// - 3。所以，这种方式不行，需要采用同步的方式，而采用同步的方式注定无法支撑大型系统，所以这种方案适用于中小型系统
//		kafkaTemplate.setProducerListener(new ProducerListener<String, String>() {
//			@Override
//			public void onSuccess(ProducerRecord<String, String> producerRecord, RecordMetadata recordMetadata) {
//
//			}
//
//			@Override
//			public void onError(ProducerRecord<String, String> producerRecord, Exception exception) {
//
//			}
//		});
	}
}
