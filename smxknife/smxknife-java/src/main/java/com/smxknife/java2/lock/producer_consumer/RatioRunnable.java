package com.smxknife.java2.lock.producer_consumer;

import lombok.Getter;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/13
 */
public abstract class RatioRunnable implements Runnable {

	@Getter
	private String name;
	@Getter
	private Repository repository;
	private static final Random RANDOM = new Random();

	public RatioRunnable(String name, Repository repository) {
		this.name = name;
		this.repository = repository;
	}

	protected int ratio() {
		return RANDOM.nextInt(5);
	}

	@Override
	public final void run() {
		while (true) {
			try {
				action();
				TimeUnit.SECONDS.sleep(ratio());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	protected abstract void action();
}
