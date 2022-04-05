package com.smxknife.java2.lock.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 独占锁
 * @author smxknife
 * 2020/6/3
 */
public class ExclusiveLock implements Lock {

	private final Sync sync = new Sync();

	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(time));
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		return sync.newCondition();
	}

	private static class Sync extends AbstractQueuedSynchronizer {

		// 是否独占
		@Override
		protected boolean isHeldExclusively() {
			return Thread.currentThread() == getExclusiveOwnerThread();
		}

		// 通过state维护当前是否有线程拿到锁，获取锁也就是通过CAS的方式将state从0设置为1,成功后将独占锁设置为自己
		@Override
		protected boolean tryAcquire(int arg) {
			if (compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			// 这里虽然会返回false，但是这里配合的是acquire方法，如果false，acquire会重新添加到队列中
			return false;
		}

		// 这地方不用使用CAS的原因是释放锁只有当前拿到锁的一个线程可以释放，所以不存在竞争条件
		// 但是这里也不能通过state进行判断，我在网上看到一篇文章这里采用state是否等于0进行判断，如果等于0抛出异常，如果不等于0进行后面设置，这
		// 明显是错误的
		@Override
		protected boolean tryRelease(int arg) {
			if (Thread.currentThread() != getExclusiveOwnerThread()) {
				throw new IllegalMonitorStateException();
			}
			setExclusiveOwnerThread(null);
			setState(0);
			return true;
		}

		Condition newCondition() {
			return new ConditionObject();
		}
	}
}
