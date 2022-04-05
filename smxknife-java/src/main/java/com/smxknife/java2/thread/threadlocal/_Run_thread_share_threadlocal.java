package com.smxknife.java2.thread.threadlocal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/9/8
 */
public class _Run_thread_share_threadlocal {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("hello");
		list.add("test");
		ThreadLocal<List<String>> local = new ThreadLocal<>();
		local.set(list);

		Stream.iterate(0, i -> i + 1).limit(10)
				.forEach(idx -> {
					new Thread(() -> {
						List<String> strings = local.get();
						System.out.println(Thread.currentThread().getName() + " | " + strings);
					}, "th-" + idx).start();
				});

		System.out.println(Thread.currentThread().getName() + " | " + local.get());

		System.out.println("通过输出结果可以发现，ThreadLocal定义在main线程里，在其他线程中获取ThreadLocal的值是null" +
				"，在main线程里面是可以获取值的");

	}
}
