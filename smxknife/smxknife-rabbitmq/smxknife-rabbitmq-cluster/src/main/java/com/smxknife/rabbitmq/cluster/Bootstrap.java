package com.smxknife.rabbitmq.cluster;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author smxknife
 * 2020/5/6
 */
public class Bootstrap {
	public static void main(String[] args) {

		ScheduledExecutorService producerService = Executors.newScheduledThreadPool(5);
		//producerService.scheduleAtFixedRate(new Producer1(), 0, 5, TimeUnit.SECONDS);

		ExecutorService consumerService = Executors.newFixedThreadPool(5);
		consumerService.execute(new Consumer1());
		consumerService.execute(new Consumer2());
	}
}
