package com.smxknife.java2.lock.custom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

import static java.lang.Thread.currentThread;

/**
 * @author smxknife
 * 2020/4/11
 */
public class BooleanLock implements Lock {

	private Thread currentThread;
	private boolean locked = false;
	private final List<Thread> blockedList = new ArrayList<>();

	@Override
	public void lock() throws InterruptedException {
		synchronized (this) {
			// 这个地方对locked加锁简直是一个重大的隐患，只要还有其他地方也对locked加锁，那么会出现莫名其妙的问题
			while (locked) {
				final Thread tmpThread = currentThread();
				try {
					if (!blockedList.contains(tmpThread)) {
						blockedList.add(tmpThread);
					}
					this.wait();
				} catch (InterruptedException e) {
					// 如果当前线程在wait中断，则从blockedList移除
					blockedList.remove(tmpThread);
					throw e;
				}
			}
			blockedList.remove(currentThread());
			this.locked = true;
			this.currentThread = currentThread();
		}
	}

	@Override
	public void lock(long mills) throws InterruptedException, TimeoutException {
		synchronized (this) {
			if (mills <= 0) {
				this.lock();
			} else {
				long remainingMills = mills;
				long endMills = System.currentTimeMillis() + remainingMills;
				while (locked) {
					if (remainingMills <= 0) {
						throw new TimeoutException("can not get the lock during " + mills);
					}
					if (!blockedList.contains(currentThread())) {
						blockedList.add(currentThread());
					}
					this.wait(remainingMills);
					remainingMills = endMills - System.currentTimeMillis();
				}
				blockedList.remove(currentThread());
				this.locked = true;
				this.currentThread = currentThread();
			}
		}
	}

	@Override
	public void unlock() {
		synchronized (this) {
			if (currentThread == currentThread()) {
				this.locked = false;
				this.notifyAll();
			}
		}
	}

	@Override
	public List<Thread> getBlockedThreads() {
		return Collections.unmodifiableList(blockedList);
	}
}
