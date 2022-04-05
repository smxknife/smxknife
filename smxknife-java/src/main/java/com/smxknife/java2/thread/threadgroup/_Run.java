package com.smxknife.java2.thread.threadgroup;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/9/6
 */
public class _Run {
	public static void main(String[] args) throws InterruptedException {
		ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
		System.out.println("activeCount " + threadGroup.activeCount());
		System.out.println("parent name " + threadGroup.getParent().getName());
		System.out.println("activeGroupCount " + threadGroup.activeGroupCount());
		System.out.println("getName " + threadGroup.getName());
		System.out.println("getParent " + threadGroup.getParent());
		System.out.println("isDaemon " + threadGroup.isDaemon());
		System.out.println("getMaxPriority " + threadGroup.getMaxPriority());

		Thread[] threads = Stream.iterate(0, i -> i + 1).limit(5).map(idx -> {
			return new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			}, "thread-idx-" + idx);
		}).toArray(Thread[]::new);

		// 这是一个错误的用法
		System.out.println("这是一个错误的用法");
		System.out.println("enumerate之前，threads的长度：" + threads.length + ", 线程名如下：");
		Arrays.asList(threads).forEach(th -> {
			System.out.println(" " + th.getName());
		});
		System.out.println("-------");
		int enumerate = threadGroup.enumerate(threads);
		System.out.println("enumerate " + enumerate);
		System.out.println("enumerate之后，threads的长度：" + threads.length + ", 线程名如下：");
		Arrays.asList(threads).forEach(th -> {
			System.out.println(th.getName());
		});
		System.out.println("-------");

		System.out.println("从输出的线程名字来看，enumerate的效果是将group里面当前活着的线程拷贝到数组里面，所以，应该传一个空数组，而不是已经创建好的数组");

		TimeUnit.SECONDS.sleep(10);
	}
}
