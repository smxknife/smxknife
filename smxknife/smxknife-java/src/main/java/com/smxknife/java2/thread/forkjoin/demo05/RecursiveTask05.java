package com.smxknife.java2.thread.forkjoin.demo05;

import lombok.AllArgsConstructor;

import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/8/31
 */
@AllArgsConstructor
public class RecursiveTask05 extends RecursiveTask<String> {

	private String name;
	private int beginValue;
	private int endValue;

	@Override
	protected String compute() {
		System.out.println(Thread.currentThread().getName() + " ========== " + name + " | " + beginValue + " " + endValue);
		if (endValue - beginValue > 2) {
			int middleNum = (beginValue + endValue) / 2;
			RecursiveTask05 leftTask = new RecursiveTask05("left", beginValue, middleNum);
			RecursiveTask05 rightTask = new RecursiveTask05("right", middleNum + 1, endValue);

			invokeAll(leftTask, rightTask);
			return leftTask.join() + rightTask.join();
		} else {
			String val = Stream.iterate(beginValue, i -> i + 1).limit(endValue - beginValue + 1)
					.map(String::valueOf)
					.collect(Collectors.joining(", ", "[", "]"));
			System.out.printf(Thread.currentThread().getName() + " %s | 打印组合为：%s - %s\r\n", name, beginValue, endValue);
			return val;
		}
	}
}
