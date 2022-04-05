package com.smxknife.java2.thread.threadlocal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/9/8
 */
public class _Run_wrong {

	static ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();

	@Deprecated
	public static void main(String[] args) {
		// TODO 这简直不能称为正确的demo，不知道网上某些人为什么会这么理解
		Stream.iterate(0, i -> i + 1).limit(10)
				.forEach(idx -> {
					new Thread(() -> {
						ThreadLocal<List<String>> local = threadLocal;
						local.set(Arrays.asList("hello, world"));
						List<String> strings = local.get();
						System.out.println(Thread.currentThread().getName() + " start| " + strings);
						strings.replaceAll(s -> s + "_" + idx);
						System.out.println(Thread.currentThread().getName() + " end| " + strings);
					}, "th-" + idx).start();
				});

		System.out.println("这个例子就是从网上找的，感觉这种做法。。。完全就是没有必要，没有变量共享，用ThreadLocal干嘛！！！！");
	}
}
