package com.smxknife.java2.lock.Lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @author smxknife
 * 2020/7/9
 */
public class StampedLockDemo {
	public static void main(String[] args) {
		StampedLockObje obje = new StampedLockObje();
		//ReadWriteLockObje obje = new ReadWriteLockObje();
		ExecutorService service = Executors.newFixedThreadPool(10);

		Runnable readTask = () -> {
			while (true) {
				obje.read();
			}
		};

		Runnable writeTask = () -> {
			while (true) {
				obje.write();
			}
		};

		Runnable optimisticReadTask = () -> {
			while (true) {
				obje.optimisticRead();
			}
		};

		for (int i = 0; i < 9; i++) {
			service.submit(optimisticReadTask);
		}
		service.submit(writeTask);
	}

	static class StampedLockObje {
		private StampedLock lock = new StampedLock();
		private List<String> data = new ArrayList<>();

		public void write() {
			long stamped = -1;
			stamped = lock.writeLock();
			try {
				String string = "Write | " + Thread.currentThread().getName() + " |" + stamped;
				data.add(string);
				System.out.println(string);
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlockWrite(stamped);
			}
		}

		public void read() {
			long stamped = -1;
			stamped = lock.readLock();
			try {
				data.forEach(val -> System.out.println("Read(" + Thread.currentThread().getName() + ") : --- " + val));
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlockRead(stamped);
			}
		}

		public void optimisticRead() {
			long stamped = -1;
			// 尝试获取一个读锁
			stamped = lock.tryOptimisticRead();
			// 如果没有线程修改，我们再去获取一个读锁
			if (!lock.validate(stamped)) {
				try {
					stamped = lock.readLock();
					data.forEach(val -> System.out.println("Read(" + Thread.currentThread().getName() + ") : --- " + val));
					TimeUnit.SECONDS.sleep(1);
				} catch (Exception e) {

				} finally {
					lock.unlockRead(stamped);
				}
			}
		}
	}

	static class ReadWriteLockObje {
		private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		private List<String> data = new ArrayList<>();

		public void write() {
			long stamped = -1;
			ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
			writeLock.lock();
			try {
				String string = "Write | " + Thread.currentThread().getName() + " |" + stamped;
				data.add(string);
				System.out.println(string);
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				writeLock.unlock();
			}
		}

		public void read() {
			long stamped = -1;
			ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
			readLock.lock();
			try {
				data.forEach(val -> System.out.println("Read(" + Thread.currentThread().getName() + ") : --- " + val));
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				readLock.unlock();
			}
		}

		public void optimisticRead() {}
	}
}
