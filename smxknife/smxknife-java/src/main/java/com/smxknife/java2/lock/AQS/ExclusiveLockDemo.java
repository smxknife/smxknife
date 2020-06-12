package com.smxknife.java2.lock.AQS;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2020/6/3
 */
public class ExclusiveLockDemo {
	public static void main(String[] args) throws InterruptedException {
		Counter counter = new Counter();

		List<Thread> collect = IntStream.iterate(0, idx -> idx + 1).limit(30).mapToObj(idx -> new Thread(() -> {
			try {
				counter.exclusiveIncrement();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, "th-" + idx)).collect(Collectors.toList());

		collect.forEach(Thread::start);
		TimeUnit.SECONDS.sleep(5);

	}
}
