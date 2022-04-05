package com.smxknife.java2.thread.threadlocal;

import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/9/8
 */
public class _Run_ok_with_object {
	static ThreadLocal<Shared> local = new ThreadLocal() {
		@Override
		protected Shared initialValue() {
			Shared shared = new Shared();
			shared.name = "default";
			shared.age = 11;
			return shared;
		}
	};

	// TODO 这里需要注意的是set方法，set方法其实是对当前线程有效，在其他线程中是无法获取的，也就是说set设置的值是无法共享的
//	static ThreadLocal<Shared> local = new ThreadLocal<>();
//	static {
//		Shared shared = new Shared();
//		shared.name = "default";
//		shared.age = -1;
//		local.set(shared);
//	}

	private static class Shared {
		String name;
		int age;
	}

	public static void main(String[] args) {
		Stream.iterate(0, i -> i + 1).limit(10)
				.forEach(idx -> {
					Thread thread = new Thread(() -> {
						Shared shared = local.get();
						System.out.println(Thread.currentThread().getName() + " | " + shared);
						System.out.println(Thread.currentThread().getName() + " | " + shared.name + ", " + shared.age);
						shared.name = Thread.currentThread().getName();
						shared.age = Thread.currentThread().getPriority();
						System.out.println(Thread.currentThread().getName() + " | " + shared.name + ", " + shared.age);
						local.remove(); // 看网上说加上这一句可以防止threadLocal占用的内存过大，别急，回头看源码
					}, "th-" + idx);
					thread.start();
				});
		System.out.println("从这个输出的结果可以看出来，从local获取的对象每一个都是一个新的对象，他们的id是不一样的\r\n" +
				"这样，就可以通过设置一个默认值后，根据每个线程的使用情况加以修改，而不会影响其他线程\r\n" +
				"这才是threadloca正确的使用方法，像网上的一些例子是不太恰当的\r\n");
	}
}
