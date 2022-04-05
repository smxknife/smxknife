package com.smxknife.java2.thread.threadlock;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/9/19
 */
public class _Run_ObjectHasLock {
	public static void main(String[] args) {

		ObjectHasLock objectLock = new ObjectHasLock();

		List<Thread> collect = Stream.iterate(0, i -> i + 1).limit(10)
				.map(idx -> {
					return new Thread(() -> {
						objectLock.setName(Thread.currentThread().getName());
						objectLock.getName();
					}, "test-thread-" + idx);
				}).collect(Collectors.toList());

		collect.stream().unordered().forEach(Thread::start);
	}
}
