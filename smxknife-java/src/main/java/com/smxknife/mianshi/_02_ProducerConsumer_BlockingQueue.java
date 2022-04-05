package com.smxknife.mianshi;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smxknife
 * 2021/5/19
 */
public class _02_ProducerConsumer_BlockingQueue {
	public static void main(String[] args) {
		Factory factory = new Factory();

		for (int i = 0; i < 2; i++) {
			new Producer(factory, "p" + i).start();
		}

		for (int i = 0; i < 10; i++) {
			new Consumer(factory, "c" + i).start();
		}
	}

	private static class Factory {

		private BlockingQueue<Object> queue = new ArrayBlockingQueue<>(100);
		private static final int cap = 100;
		private AtomicInteger integer = new AtomicInteger();

		void put() throws InterruptedException {

			final int e = integer.incrementAndGet();
			queue.put(e);
			System.out.println(Thread.currentThread().getName() + " put | " + e);


		}

		/**
		 * 版本1
		 * @return
		 */
		Object get() throws InterruptedException {
			return queue.take();
		}
	}

	private static class Producer extends Thread {

		private Factory factory;

		public Producer(Factory factory, String name) {
			this.factory = factory;
			this.setName(name);
		}

		@Override
		public void run() {
			try {
				while (true) {
					factory.put();
					TimeUnit.SECONDS.sleep(1);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static class Consumer extends Thread {

		private Factory factory;

		public Consumer(Factory factory, String name) {
			this.factory = factory;
			this.setName(name);
		}

		@SneakyThrows
		@Override
		public void run() {
			while (true) {
				final Object o = this.factory.get();
				System.out.println(Thread.currentThread().getName() + " | take " + o);
			}
		}
	}
}
