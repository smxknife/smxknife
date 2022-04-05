package com.smxknife.java2.thread.completionservice.demo01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author smxknife
 * 2019/8/27
 */
public class _Run_take {
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

			// take方法是取得最先完成任务的Future对象，谁执行时间短，谁最先返回
			// 但是take是阻塞的
			for (int i = 0; i < 6; i++) {
				System.out.println("等待第 " + (i + 1) + " 个take()");
				System.out.println(completionService.take().get());
			}

			// 按乱序打印的效果
			// 说明一个Future对应当前先执行完的Callable任务

			executor.shutdown();

		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
