package com.smxknife.java2.lock.producer_consumer.reentrantlock;

import com.smxknife.java2.lock.producer_consumer.Repository;

import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author smxknife
 * 2019/9/13
 */
public class ReentrantLockRepository<E> implements Repository<E> {

	private final Lock lock = new ReentrantLock();
	private final Condition consumeCond = lock.newCondition();
	private final Condition produceCond = lock.newCondition();

	private final Object[] items;
	private int tail, head, count;

	public ReentrantLockRepository(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("capacity must be gather 0");
		}
		this.items = new Object[capacity];
	}

	@Override
	public void produce(E e) {
		Objects.requireNonNull(e);

		lock.lock();

		try {
			while (count >= items.length) {
				produceCond.await();
			}
			items[tail++] = e;
			count++;
			if (tail == items.length) {
				tail = 0;
			}
			this.consumeCond.signal();

		} catch (InterruptedException th) {
			th.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	@Override
	public E consume() {

		lock.lock();

		try {
			while (this.count <= 0) {
				this.count = 0;
				this.consumeCond.await();
			}

			E e = (E) this.items[head];
			this.items[head++] = null;
			this.count--;
			if (this.head == this.items.length) {
				this.head = 0;
			}
			this.produceCond.signal();
			return e;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		return null;
	}
}
