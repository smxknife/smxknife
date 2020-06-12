package com.smxknife.disruptor.demo3_multi_consumer;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author smxknife
 * 2020/3/6
 */
@Slf4j
public class ThreadPoolMonitor extends ThreadPoolExecutor {
	/**
	 * 保存任务开始执行的时间，当任务结束时，用任务结束时间减去开始时间计算任务执行时间
	 */
	private ConcurrentHashMap<Object, LocalTime> startTimes;

	/**
	 * 线程池名称，一般以业务名称命名，方便区分
	 */
	private String poolName;

	/**
	 * 调用父类的构造方法，并初始化HashMap和线程池名称
	 *
	 * @param corePoolSize    线程池核心线程数
	 * @param maximumPoolSize 线程池最大线程数
	 * @param keepAliveTime   线程的最大空闲时间
	 * @param unit            空闲时间的单位
	 * @param workQueue       保存被提交任务的队列
	 * @param poolName        线程池名称
	 */
	public ThreadPoolMonitor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
	                         TimeUnit unit, BlockingQueue<Runnable> workQueue, String poolName) {
		this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				Executors.defaultThreadFactory(), poolName);
	}

	/**
	 * 调用父类的构造方法，并初始化HashMap和线程池名称
	 *
	 * @param corePoolSize    线程池核心线程数
	 * @param maximumPoolSize 线程池最大线程数
	 * @param keepAliveTime   线程的最大空闲时间
	 * @param unit            空闲时间的单位
	 * @param workQueue       保存被提交任务的队列
	 * @param threadFactory   线程工厂
	 * @param poolName        线程池名称
	 */
	public ThreadPoolMonitor(int corePoolSize, int maximumPoolSize, long keepAliveTime,
	                         TimeUnit unit, BlockingQueue<Runnable> workQueue,
	                         ThreadFactory threadFactory, String poolName) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
		this.startTimes = new ConcurrentHashMap<>();
		this.poolName = poolName;
	}

	/**
	 * 线程池延迟关闭时（等待线程池里的任务都执行完毕），统计线程池情况
	 */
	@Override
	public void shutdown() {
		// 统计已执行任务、正在执行任务、未执行任务数量
		log.info("{} Going to shutdown. TaskCounts: {}, Executed tasks: {}, Running tasks: {}, Pending tasks: {}",
				this.poolName, this.getTaskCount(), this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size());
		super.shutdown();
	}

	/**
	 * 线程池立即关闭时，统计线程池情况
	 */
	@Override
	public List<Runnable> shutdownNow() {
		log.info("{} Going to immediately shutdown. TaskCounts: {}, Executed tasks: {}, Running tasks: {}, Pending tasks: {}",
				this.poolName, this.getTaskCount(), this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size());
		return super.shutdownNow();
	}

	/**
	 * 任务执行之前，记录任务开始时间
	 */
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		if (startTimes.contains(r.hashCode())) {
			log.warn("---- task has started");
			return;
		}
		startTimes.putIfAbsent(r.hashCode(), LocalTime.now());
	}

	/**
	 * 任务执行之后，计算任务结束时间
	 */
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		LocalTime endTime = LocalTime.now();
		LocalTime startTime = startTimes.remove(r.hashCode());
		long diff = Duration.between(startTime, endTime).toMillis();
		log.info("{}-pool-monitor: " +
						"Duration: {} ms, PoolSize: {}, CorePoolSize: {}, Active: {}, " +
						"Completed: {}, Task: {}, Queue: {}, LargestPoolSize: {}, " +
						"MaximumPoolSize: {},  KeepAliveTime: {}, isShutdown: {}, isTerminated: {}",
				this.poolName,
				diff, this.getPoolSize(), this.getCorePoolSize(), this.getActiveCount(),
				this.getCompletedTaskCount(), this.getTaskCount(), this.getQueue().size(), this.getLargestPoolSize(),
				this.getMaximumPoolSize(), this.getKeepAliveTime(TimeUnit.MILLISECONDS), this.isShutdown(), this.isTerminated());
	}

	/**
	 * 创建固定线程池，代码源于Executors.newFixedThreadPool方法，这里增加了poolName
	 *
	 * @param nThreads 线程数量
	 * @param poolName 线程池名称
	 * @return ExecutorService对象
	 */
	public static ExecutorService newFixedThreadPool(int nThreads, String poolName) {
		return new ThreadPoolMonitor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), poolName);
	}

	/**
	 * 创建缓存型线程池，代码源于Executors.newCachedThreadPool方法，这里增加了poolName
	 *
	 * @param poolName 线程池名称
	 * @return ExecutorService对象
	 */
	public static ExecutorService newCachedThreadPool(String poolName) {
		return new ThreadPoolMonitor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), poolName);
	}

	/**
	 * 生成线程池所用的线程，只是改写了线程池默认的线程工厂，传入线程池名称，便于问题追踪
	 */
	static class EventThreadFactory implements ThreadFactory {
		private static final AtomicInteger poolNumber = new AtomicInteger(1);
		private final ThreadGroup group;
		private final AtomicInteger threadNumber = new AtomicInteger(1);
		private final String namePrefix;

		/**
		 * 初始化线程工厂
		 *
		 * @param poolName 线程池名称
		 */
		EventThreadFactory(String poolName) {
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
