package com.smxknife.java2.thread.threadlocal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/9/8
 */
public class _Run_thread_inner_threadlocal {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("hello");
		list.add("test");

		Stream.iterate(0, i -> i + 1).limit(10)
				.forEach(idx -> {
					new Thread(() -> {
						ThreadLocal<List<String>> local = new ThreadLocal<>();
						local.set(list);
						List<String> strings = local.get();
						System.out.println(Thread.currentThread().getName() + " start| " + strings);
						strings.replaceAll(s -> s + "_" + idx);
						System.out.println(Thread.currentThread().getName() + " end| " + strings);
					}, "th-" + idx).start();
				});

		System.out.println("通过输出发现，虽然每个线程都独自创建了ThreadLocal，但是对于集合中的对象还是共享的");
		// 输出结果如下：
		/**
		 * th-0 start| [hello, test]
		 * th-0 end| [hello_0, test_0]
		 * th-1 start| [hello_0, test_0]
		 * th-1 end| [hello_0_1, test_0_1]
		 * th-2 start| [hello_0_1, test_0_1]
		 * th-2 end| [hello_0_1_2, test_0_1_2]
		 * th-3 start| [hello_0_1_2, test_0_1_2]
		 * th-3 end| [hello_0_1_2_3, test_0_1_2_3]
		 * th-4 start| [hello_0_1_2_3, test_0_1_2_3]
		 * th-4 end| [hello_0_1_2_3_4, test_0_1_2_3_4]
		 * th-5 start| [hello_0_1_2_3_4, test_0_1_2_3_4]
		 * th-5 end| [hello_0_1_2_3_4_5, test_0_1_2_3_4_5]
		 * th-6 start| [hello_0_1_2_3_4_5, test_0_1_2_3_4_5]
		 * th-6 end| [hello_0_1_2_3_4_5_6, test_0_1_2_3_4_5_6]
		 * th-7 start| [hello_0_1_2_3_4_5_6, test_0_1_2_3_4_5_6]
		 * th-7 end| [hello_0_1_2_3_4_5_6_7, test_0_1_2_3_4_5_6_7]
		 * th-8 start| [hello_0_1_2_3_4_5_6_7, test_0_1_2_3_4_5_6_7]
		 * th-8 end| [hello_0_1_2_3_4_5_6_7_8, test_0_1_2_3_4_5_6_7_8]
		 * th-9 start| [hello_0_1_2_3_4_5_6_7_8, test_0_1_2_3_4_5_6_7_8]
		 * th-9 end| [hello_0_1_2_3_4_5_6_7_8_9, test_0_1_2_3_4_5_6_7_8_9]
		 */

	}
}
