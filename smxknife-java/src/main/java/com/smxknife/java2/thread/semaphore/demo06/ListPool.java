package com.smxknife.java2.thread.semaphore.demo06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author smxknife
 * 2018-12-21
 */
public class ListPool {

	private int poolMaxSize = 3;
	private int semaphorePermits = 5;
	private List<String> list = new ArrayList<>();
	private Semaphore semaphore = new Semaphore(semaphorePermits);
	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public ListPool() {
		super();
		for (int i = 0; i < poolMaxSize; i++) {
			list.add("smxknife-" + i);
		}
	}

	public String get() {
		String get = null;
		try {
			semaphore.acquire();
			lock.lock();
			while (list.size() == 0) {
				condition.await();
			}
			get = list.remove(0);
			lock.unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return get;
	}

	public void put(String value) {
		lock.lock();
		list.add(value);
		condition.signalAll();
		lock.unlock();
		semaphore.release();
	}
}
