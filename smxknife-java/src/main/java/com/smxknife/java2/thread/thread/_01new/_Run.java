package com.smxknife.java2.thread.thread._01new;

import java.util.Arrays;

/**
 * @author smxknife
 * 2019/10/8
 */
public class _Run {
	public static void main(String[] args) {
		// 1. *****
		Thread th1 = new Thread();
		// 2. *****
		Runnable runnable = () -> System.out.println("runner");
		Thread th2 = new Thread(runnable);
		// 3. *****
		Thread th3 = new Thread("Thread-3");
		// 4. *****
		Thread th4 = new Thread(runnable, "Thread-4");
		// 5. *****
		ThreadGroup group = new ThreadGroup("MyGroup");
		System.out.println("MyGroup activeCount: " + group.activeCount());
		System.out.println("MyGroup activeGroupCount: " + group.activeGroupCount());
		group.list();
		Thread[] threads = new Thread[10];
		group.enumerate(threads);
		System.out.println("threads = " + Arrays.asList(threads));
		Thread th5 = new Thread(group, runnable);
		System.out.println("MyGroup activeCount: " + group.activeCount());
		System.out.println("MyGroup activeGroupCount: " + group.activeGroupCount());
		group.list();
		System.out.println("threads = " + Arrays.asList(threads));
		// 6. *****
		Thread th6 = new Thread(group, "Thread-6");
		// 7. *****
		Thread th7 = new Thread(group, runnable, "Thread-7");
		// 8. *****
		Thread th8 = new Thread(group, runnable, "Thread-8", 10);
	}
}
