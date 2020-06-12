package com.smxknife.java2.thread.executorservice.demo04;

import lombok.SneakyThrows;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2020/6/11
 */
public class _Run_SynchronousQueue {
	public static void main(String[] args) throws InterruptedException {
		//
		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 7, 10, TimeUnit.SECONDS, new SynchronousQueue<>());

		IntStream.iterate(0, idx -> idx + 1).limit(7).mapToObj(idx -> new Runnable() {
			@SneakyThrows
			@Override
			public void run() {
				System.out.println("idx = " + idx);
				TimeUnit.SECONDS.sleep(1);
			}
		}).forEach(executor::execute);

		while (true) {
			System.out.println("------------------------------");
			System.out.println("corePoolSize: " + executor.getCorePoolSize());
			System.out.println("maximumPoolSize: " + executor.getMaximumPoolSize());
			System.out.println("poolSize: " + executor.getPoolSize());
			System.out.println("activeCount: " + executor.getActiveCount());
			System.out.println("taskCount: " + executor.getTaskCount());
			System.out.println("completedTaskCount: " + executor.getCompletedTaskCount());
			System.out.println("largetstPoolSize: " + executor.getLargestPoolSize());
			System.out.println("queueSize: " + executor.getQueue().size());
			TimeUnit.MILLISECONDS.sleep(500);
		}
	}
}
