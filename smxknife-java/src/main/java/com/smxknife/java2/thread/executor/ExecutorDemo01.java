package com.smxknife.java2.thread.executor;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019-08-19
 */
public class ExecutorDemo01 {

	public static void main(String[] args) {
//		new Thread(() -> {
//			while (true) {
//				System.out.println(LocalDateTime.now().getSecond());
//				try {
//					Thread.sleep(5 * 1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}).start();
		System.out.println("---- " + LocalDateTime.now());
		ThreadFactory threadFactory =
				new ThreadFactoryBuilder().setDaemon(false).setNameFormat("jobRework-scheduler-%d").build();
		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor(threadFactory);
		ses.scheduleAtFixedRate(() -> {
			System.out.println("---- " + LocalDateTime.now() + " " + Thread.currentThread().getName());
			try {
				Thread.sleep(30 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}, 5, 10, TimeUnit.SECONDS);


	}
}
