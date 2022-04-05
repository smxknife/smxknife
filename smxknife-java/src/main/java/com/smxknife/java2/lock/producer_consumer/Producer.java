package com.smxknife.java2.lock.producer_consumer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smxknife
 * 2019/9/13
 */
public class Producer extends RatioRunnable {

	private static final AtomicInteger ver = new AtomicInteger(0);

	public Producer(String name, Repository repository) {
		super(name, repository);
	}

	@Override
	public void action() {
		Map<String, String> items = new HashMap<>();
		items.put("name", "切片" + ver.incrementAndGet());
		System.out.println("[ producer ]" + this.getName() + " produce " + items);
		this.getRepository().produce(items);

	}
}
