package com.smxknife.java2.thread.threadlock;

import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/9/19
 */
public class _Run_staticInstanceObj {

	public static void main(String[] args) {
		CountDownLatch countDownLatch = new CountDownLatch(10);

		StaticInstanceObj staticInstanceObj = new StaticInstanceObj();

		Stream.iterate(0, i -> i + 1).limit(10)
				.unordered()
				.forEach(idx -> {
					new Thread(() -> {
						countDownLatch.countDown();
						if ((idx & 1) == 1) {
							StaticInstanceObj.classInit();
						} else {
							staticInstanceObj.doSome();
						}
					}, " test-thread-" + idx).start();
				});
	}
}
