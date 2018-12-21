package com.smxknife.java2.thread.semaphore.demo04;

/**
 * acquireUninterruptibly() 使等待进入acquire的方法的线程，不允许被中断
 * @author smxknife
 * 2018-12-21
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		Service service = new Service();

		//testInterrupt(service);

		testUnInterrupt(service);

	}

	private static void testUnInterrupt(Service service) throws InterruptedException {
		ThreadA a = new ThreadA(service, false);
		a.setName("A");
		a.start();

		ThreadB b = new ThreadB(service, false);
		b.setName("B");
		b.start();

		Thread.sleep(1000);

		b.interrupt();
		System.out.println("main 中断了b");
	}

	private static void testInterrupt(Service service) throws InterruptedException {
		ThreadA a = new ThreadA(service, true);
		a.setName("A");
		a.start();

		ThreadB b = new ThreadB(service, true);
		b.setName("B");
		b.start();

		Thread.sleep(1000);

		b.interrupt();
		System.out.println("main 中断了b");
	}
}
