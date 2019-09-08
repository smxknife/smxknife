package com.smxknife.java2.thread.threadlocal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/9/8
 */
public class _Run_ok_with_list_object {
	static ThreadLocal<List<Shared>> local = new ThreadLocal() {
		@Override
		protected List<Shared> initialValue() {
			// TODO 如果这里用的是Arrays.asList，那么后面是无法做增减操作的
//			return Arrays.asList(new Shared("def1", 0), new Shared("def2", -1));

			// 这种做法是可以做增减操作
			List<Shared> list = new ArrayList<>();
			list.add(new Shared("def1", 0));
			list.add(new Shared("def2", -1));
			return list;
		}
	};

	private static class Shared {
		String name;
		int age;

		public Shared(String name, int age) {
			this.name = name;
			this.age = age;
		}
	}

	public static void main(String[] args) {
		Stream.iterate(0, i -> i + 1).limit(10)
				.forEach(idx -> {
					Thread thread = new Thread(() -> {
						List<Shared> list = local.get();
						System.out.println(Thread.currentThread().getName() + " | " + list);
						list.add(new Shared(Thread.currentThread().getName(), idx));
						System.out.println(Thread.currentThread().getName() + " | " + list);
//						local.remove(); // 看网上说加上这一句可以防止threadLocal占用的内存过大，别急，回头看源码
					}, "th-" + idx);
					thread.start();
				});
		System.out.println("当ThreadLocal中的值是集合类型时，每个线程调用get方法获取的同样是一个新的集合，包括集合中的元素都是新的，不会线程之间相互影响的\r\n" +
				"这个例子是针对普通的对象做测试，从输出结果来看，确实集合中每个元素都是新的，线程间相互不影响");

	}
}
