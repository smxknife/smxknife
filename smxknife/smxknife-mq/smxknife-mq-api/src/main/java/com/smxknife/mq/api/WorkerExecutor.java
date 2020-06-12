package com.smxknife.mq.api;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smxknife
 * 2020/5/7
 */
@Slf4j
public class WorkerExecutor extends ThreadPoolExecutor {
	private String name;

	/**
	 * 调用父类的构造方法
	 *
	 * @param name            线程池名称
	 * @param corePoolSize    线程池核心线程数
	 * @param maximumPoolSize 线程池最大线程数
	 * @param keepAliveTime   线程的最大空闲时间
	 * @param unit            空闲时间的单位
	 * @param workQueue       保存被提交任务的队列
	 */
	public WorkerExecutor(String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
	                      BlockingQueue<Runnable> workQueue) {
		this(name, corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, new WorkerThreadFactory(name));
	}

	/**
	 * 调用父类的构造方法
	 *
	 * @param name            线程池名称
	 * @param corePoolSize    线程池核心线程数
	 * @param maximumPoolSize 线程池最大线程数
	 * @param keepAliveTime   线程的最大空闲时间
	 * @param unit            空闲时间的单位
	 * @param workQueue       保存被提交任务的队列
	 * @param threadFactory   线程工厂
	 */
	public WorkerExecutor(String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
	                      BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		this.name = name;
	}

	public static WorkerExecutor newFixedThreadPool(int nThreads, String name) {
		return new WorkerExecutor(name, nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
	}

	public static WorkerExecutor newCachedThreadPool(String name) {
		return new WorkerExecutor(name,0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>());
	}

	/**
	 * 线程池延迟关闭时（等待线程池里的任务都执行完毕），统计线程池情况
	 */
	@Override
	public void shutdown() {
		// 统计已执行任务、正在执行任务、未执行任务数量
		log.info("{} Going to shutdown. TaskCounts: {}, Executed tasks: {}, Running tasks: {}, Pending tasks: {}",
				this.name, this.getTaskCount(), this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size());
		super.shutdown();
	}

	/**
	 * 线程池立即关闭时，统计线程池情况
	 */
	@Override
	public List<Runnable> shutdownNow() {
		log.info("{} Going to immediately shutdown. TaskCounts: {}, Executed tasks: {}, Running tasks: {}, Pending tasks: {}",
				this.name, this.getTaskCount(), this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size());
		return super.shutdownNow();
	}

	static class WorkerThreadFactory implements ThreadFactory {
		private static final AtomicInteger poolNumber = new AtomicInteger(1);
		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final String namePrefix;

		/**
		 * 初始化线程工厂
		 *
		 * @param poolName 线程池名称
		 */
		WorkerThreadFactory(String poolName) {
			SecurityManager s = System.getSecurityManager();
			group = Objects.nonNull(s) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
			namePrefix = poolName + "-pool-" + poolNumber.getAndIncrement() + "-thread-";
		}

		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
			if (t.isDaemon()) {
				t.setDaemon(false);
			}
			if (t.getPriority() != Thread.NORM_PRIORITY) {
				t.setPriority(Thread.NORM_PRIORITY);
			}
			return t;
		}
	}
}
