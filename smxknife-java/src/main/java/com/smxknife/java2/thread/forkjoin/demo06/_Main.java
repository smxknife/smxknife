package com.smxknife.java2.thread.forkjoin.demo06;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2020/3/18
 */
public class _Main {

	public static void main(String[] args) throws InterruptedException {
		while (true) {
			ForkJoinPool.commonPool().execute(() -> {
				IntStream.iterate(0, idx -> idx + 1).limit(500).forEach(idx -> {
					try {
						TimeUnit.MICROSECONDS.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
			});
			TimeUnit.SECONDS.sleep(2);
		}


	}
}
