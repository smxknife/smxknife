package com.smxknife.mianshi;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 固定容量的同步容器，有put和get，支持2个生产者以及10个消费者
 * 使用wait/notify
 *
 * 中间遇到的问题：
 * - 1。 想采用双锁（生产锁和消费锁）
 * -- 很容易造成死锁
 *
 *
 * @author smxknife
 * 2021/5/18
 */
public class _03_ProducerConsumer_ReentrantLock {
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

		private static final int cap = 100;
		private int count = 0;
		private ReentrantLock lock = new ReentrantLock();
		private Condition pCond = lock.newCondition();
		private Condition cCond = lock.newCondition();
		private LinkedList<Object> items = new LinkedList<>();

		void put() throws InterruptedException {
			lock.lock();
			try {
				while (this.items.size() == cap) {
					pCond.await();
				}

				items.add(items);
				this.count++;
				System.out.println(Thread.currentThread().getName() + " put | total =  " + this.count + " | size = " + items.size());
				cCond.signalAll();
			} catch (Exception e) {

			} finally {
				lock.unlock();
			}


		}

		/**
		 * 版本1
		 * @return
		 */
		Object get() {
			lock.lock();
			try {
				while (this.items.size() == 0) {
					try {
						System.out.println(Thread.currentThread().getName() + " ready to relex");
						cCond.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				this.count--;
				final Object o = items.removeFirst();
				pCond.signalAll();
				System.out.println(Thread.currentThread().getName() + " get | total = " + this.count + " | size = " + items.size());
				return o;
			} catch (Exception e) {

			} finally {
				lock.unlock();
			}
			return null;
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

		@Override
		public void run() {
			this.factory.get();
		}
	}
}
