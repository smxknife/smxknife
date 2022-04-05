package com.smxknife.cloud.netflix;

import com.smxknife.cloud.netflix.service.PayOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2021/5/29
 */
@Component
public class MqRunner implements CommandLineRunner {

	@Autowired
	private PayOrderService payOrderService;

	private ScheduledExecutorService executorService;

	@Override
	public void run(String... args) throws Exception {
		executorService.scheduleWithFixedDelay(() -> {
			payOrderService.syncOrder();
		}, 0, 1, TimeUnit.SECONDS);
	}

	@PostConstruct
	public void init() {
		executorService = Executors.newSingleThreadScheduledExecutor();
	}
}
