package com.smxknife.java2.thread.scheduledexecutorservice.demo01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author smxknife
 * 2019/8/29
 */
public class _Run_single_thread_pool {
	public static void main(String[] args) {
		List<Callable<String>> callables = new ArrayList<>();
		callables.add(new MyCallableA());
		callables.add(new MyCallableB());

		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

		ScheduledFuture<String> scheduleA = scheduledExecutorService.schedule(callables.get(0), 4, TimeUnit.SECONDS);
		ScheduledFuture<String> scheduleB = scheduledExecutorService.schedule(callables.get(1), 4, TimeUnit.SECONDS);

		System.out.println("Main enter: " + System.currentTimeMillis());
		try {
			System.out.println("CallableA: " + scheduleA.get());
			System.out.println("CallableB: " + scheduleB.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("Main fin: " + System.currentTimeMillis());
	}
}
