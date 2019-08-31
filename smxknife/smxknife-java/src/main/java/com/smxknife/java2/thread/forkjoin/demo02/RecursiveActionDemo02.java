package com.smxknife.java2.thread.forkjoin.demo02;

import lombok.AllArgsConstructor;

import java.util.concurrent.RecursiveAction;

/**
 * @author smxknife
 * 2019/8/31
 */
@AllArgsConstructor
public class RecursiveActionDemo02 extends RecursiveAction {

	private String name;
	private int beginValue;
	private int endValue;

	@Override
	protected void compute() {
		System.out.println(Thread.currentThread().getName() + " ========== " + name + " | " + beginValue + " " + endValue);
		if (endValue - beginValue > 2) {
			int middleNum = (beginValue + endValue) / 2;
			RecursiveActionDemo02 leftAction = new RecursiveActionDemo02("left", beginValue, middleNum);
			RecursiveActionDemo02 rightAction = new RecursiveActionDemo02("right", middleNum + 1, endValue);

			invokeAll(leftAction, rightAction);
		} else {
			System.out.printf(Thread.currentThread().getName() + " %s | 打印组合为：%s - %s\r\n", name, beginValue, endValue);
		}
	}
}
