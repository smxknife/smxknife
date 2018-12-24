package com.smxknife.java2.thread.semaphore.demo07;

import java.util.Objects;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2018-12-21
 */
public class RepastService {
	volatile private Semaphore setSemaphore = new Semaphore(10); // producer
	volatile private Semaphore getSemaphore = new Semaphore(20); // consumer
	volatile private ReentrantLock lock = new ReentrantLock();
	volatile private Condition setCondition = lock.newCondition();
	volatile private Condition getCondition = lock.newCondition();
	// 产品容器最多4个
	volatile private Object[] productionPosition = new Object[4];

	private boolean isEmpty() {
		return Stream.of(productionPosition).allMatch(Objects::isNull);
	}

	private boolean isFull() {
		return Stream.of(productionPosition).noneMatch(Objects::isNull);
	}

	public void set() {
		try {
			setSemaphore.acquire();
			lock.lock();
			while (isFull()) {
				// 生产者等待
				setCondition.await();
			}
			Stream.iterate(0, idx -> idx + 1).limit(4)
					.filter(idx -> Objects.isNull(productionPosition[idx]))
					.unordered().findAny()
					.ifPresent(idx -> {
						productionPosition[idx] = "数据_" + idx;
						System.out.println(Thread.currentThread().getName() + " 生产了 " + productionPosition[idx]);
					});
			getCondition.signalAll();;
			lock.unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			setSemaphore.release();
		}
	}

	public void get() {
		try {
			getSemaphore.acquire();
			lock.lock();
			while (isEmpty()) {
				getCondition.await();
			}
			Stream.iterate(0, idx -> idx + 1).limit(4)
					.filter(idx -> Objects.nonNull(productionPosition[idx]))
					.unordered().findAny()
					.ifPresent(idx -> {
						System.out.println(Thread.currentThread().getName() + " 消费了 " + productionPosition[idx]);
						productionPosition[idx] = null;
					});
			setCondition.signalAll();
			lock.unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			getSemaphore.release();
		}
	}
}
