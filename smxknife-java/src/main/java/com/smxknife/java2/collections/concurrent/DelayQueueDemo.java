package com.smxknife.java2.collections.concurrent;

import lombok.ToString;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author smxknife
 * 2019/9/9
 */
public class DelayQueueDemo {
	public static void main(String[] args) {
		DelayQueue<DelayRunnable> delayQueue = new DelayQueue();

		// 添加数据线程池
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.scheduleAtFixedRate(() -> {
			boolean offer = delayQueue.offer(new DelayRunnable(), 1, TimeUnit.SECONDS);
			if (!offer) {
				System.out.println("1s内添加失败");
			} else {
				System.out.println("添加成功 | queue = " + delayQueue);
			}
		}, 0, 2, TimeUnit.SECONDS);

		// 消费线程
		ScheduledExecutorService takeThreadPool = Executors.newScheduledThreadPool(1);
		takeThreadPool.scheduleAtFixedRate(() -> {
			try {
				System.out.println(Thread.currentThread().getName() + ": takeThreadPool");
				delayQueue.take().run();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, 0, 2, TimeUnit.SECONDS);

		// 消费线程
		ScheduledExecutorService drainToThreadPool = Executors.newScheduledThreadPool(1);
		drainToThreadPool.scheduleAtFixedRate(() -> {
			try {
				System.out.println(Thread.currentThread().getName() + ": drainToThreadPool");
				List<DelayRunnable> runnables = new ArrayList<>();
				delayQueue.drainTo(runnables);
				runnables.forEach(DelayRunnable::run);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, 0, 3, TimeUnit.SECONDS);
	}

	@ToString
	static class DelayRunnable implements Delayed {

		private final LocalTime end;

		public DelayRunnable() {
			end = LocalTime.now().plusSeconds(5);
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(Duration.between(LocalTime.now(), end).toMillis(), TimeUnit.MILLISECONDS);
		}

		@Override
		public int compareTo(Delayed o) {
			long compare = this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS);
			if (compare < 0) {
				return -1;
			} else if (compare > 0) {
				return 1;
			} else {
				return 0;
			}
		}

		public void run() {
			System.out.println(String.format("%s | end = %s", Thread.currentThread().getName(), end));
		}
	}
}
