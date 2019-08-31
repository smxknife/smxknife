package com.smxknife.java2.thread.completionservice.demo01;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author smxknife
 * 2019/8/27
 */
public class _Run_poll {
	public static void main(String[] args) {
		try {
			MyCallable call1 = new MyCallable("username1", 5);
			MyCallable call2 = new MyCallable("username2", 4);
			MyCallable call3 = new MyCallable("username3", 3);
			MyCallable call4 = new MyCallable("username4", 2);
			MyCallable call5 = new MyCallable("username5", 1);

			List<Callable> callables = new ArrayList<>();
			callables.add(call1);
			callables.add(call2);
			callables.add(call3);
			callables.add(call4);
			callables.add(call5);

			ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
			ExecutorCompletionService<Callable> completionService = new ExecutorCompletionService<>(executor);

			callables.forEach(completionService::submit);

			// poll获取并移除下一个已经完成的Future，如果不存在返回null
			// poll是无阻塞的
			for (int i = 0; i < 6; i++) {
				System.out.println("等待第 " + (i + 1) + " 个poll()");
				Future<Callable> pollFuture = completionService.poll();
				if (Objects.isNull(pollFuture)) {
					System.out.println("第 " + (i + 1) + " 次poll()没有已完成的任务");
				} else {
					System.out.println(pollFuture.get());
				}
			}

			int i = 0;
			while (i < 6) {
				Future<Callable> pollFuture = completionService.poll();
				if (Objects.isNull(pollFuture)) {
					System.out.println("第 " + (i + 1) + " 个任务还没有完成");
					TimeUnit.SECONDS.sleep(1);
				} else {
					System.out.println(pollFuture.get());
					i++;
				}
			}
			executor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
