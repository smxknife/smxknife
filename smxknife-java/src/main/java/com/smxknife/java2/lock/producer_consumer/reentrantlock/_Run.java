package com.smxknife.java2.lock.producer_consumer.reentrantlock;

import com.smxknife.java2.lock.producer_consumer.Consumer;
import com.smxknife.java2.lock.producer_consumer.Producer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/9/13
 */
public class _Run {
	public static void main(String[] args) {
		ReentrantLockRepository breadRepository = new ReentrantLockRepository(10);

		ExecutorService producersPool = Executors.newFixedThreadPool(3);
		ExecutorService consumersPool = Executors.newFixedThreadPool(10);

		Stream.iterate(0, i -> i + 1).limit(3)
				.forEach(idx -> producersPool.execute(new Producer("producer-" + idx, breadRepository)));

		Stream.iterate(0, i -> i + 1).limit(10)
				.forEach(idx -> consumersPool.execute(new Consumer("consumer-" + idx, breadRepository)));

		System.out.println("main");

	}
}
