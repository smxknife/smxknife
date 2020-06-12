package com.smxknife.java2.lock.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author smxknife
 * 2020/6/3
 */
public class SharedLock implements Lock {

	private final Sync sync;

	public SharedLock(int acquires) {
		sync = new Sync(acquires);
	}

	@Override
	public void lock() {
		sync.acquireShared(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireSharedInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquireShared(1) > 0;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.releaseShared(1);
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

	private static final class Sync extends AbstractQueuedSynchronizer {
		Sync(int count) {
			if (count < 1) {
				throw new IllegalArgumentException("count less 1");
			}
			setState(count);
		}

		@Override
		protected int tryAcquireShared(int acquires) {
			for (;;) {
				int available = getState();
				int remaining = available - acquires;
				if (remaining < 0 || compareAndSetState(available, remaining)) {
					return remaining;
				}
			}
		}

		@Override
		protected boolean tryReleaseShared(int releases) {
			for (;;) {
				int current = getState();
				int next = current + releases;
				if (next < current) {// overflow
					throw new Error("Maximum permit count exceeded");
				}
				if (compareAndSetState(current, next)) {
					return true;
				}
			}
		}

		final Condition newCondition() {
			return new ConditionObject();
		}
	}
}
