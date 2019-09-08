package com.smxknife.java2.thread.threadlocal;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/9/8
 */
public class _Run_ok_with_list {
	static ThreadLocal<List<String>> local = new ThreadLocal() {
		@Override
		protected List<String> initialValue() {
			return Arrays.asList("hello", "test");
		}
	};

	public static void main(String[] args) {
		Stream.iterate(0, i -> i + 1).limit(10)
				.forEach(idx -> {
					Thread thread = new Thread(() -> {
						List<String> list = local.get();
						System.out.println(Thread.currentThread().getName() + " | " + list);
						list.replaceAll(s -> s + "_" + Thread.currentThread().getName());
						System.out.println(Thread.currentThread().getName() + " | " + list);
						local.remove(); // 看网上说加上这一句可以防止threadLocal占用的内存过大，别急，回头看源码
					}, "th-" + idx);
					thread.start();
				});
		System.out.println("当ThreadLocal中的值是集合类型时，每个线程调用get方法获取的同样是一个新的集合，包括集合中的元素都是新的，不会线程之间相互影响的");
	}
}
