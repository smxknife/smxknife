package com.smxknife.java2.lock.AQS;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author smxknife
 * 2019/8/25
 */
public class AbstractQueuedSynchronizerDemo {
	public static void main(String[] args) {

	}

	private static class Sync extends AbstractQueuedSynchronizer {
		@Override
		protected boolean tryAcquire(int arg) {
			return super.tryAcquire(arg);
		}

		@Override
		protected boolean tryRelease(int arg) {
			return super.tryRelease(arg);
		}

		@Override
		protected int tryAcquireShared(int arg) {
			return super.tryAcquireShared(arg);
		}

		@Override
		protected boolean tryReleaseShared(int arg) {
			return super.tryReleaseShared(arg);
		}

		@Override
		protected boolean isHeldExclusively() {
			return super.isHeldExclusively();
		}

		@Override
		public String toString() {
			return super.toString();
		}
	}
}
