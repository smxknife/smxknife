package com.smxknife.java2.thread.forkjoin.demo03;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @author smxknife
 * 2019/8/31
 */
public class _Run_RecursiveTask_join_Exception {
	public static void main(String[] args) {
		try {
			ForkJoinPool pool = new ForkJoinPool();
			RecursiveTask03_Exception task03 = new RecursiveTask03_Exception();
			System.out.println(task03.hashCode());

			ForkJoinTask<Integer> forkJoinTask = pool.submit(task03);
			System.out.println(forkJoinTask.hashCode() + " " + forkJoinTask.join());
		}

		// 加上catch会编译就报错，因为join方法就不会抛出任何异常

//		catch (InterruptedException e) {
//			System.out.println("enter interrupt");
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			System.out.println("enter execution");
//			e.printStackTrace();
//		}

		finally {
			// 加finally是为了try能把catch注释掉后还不报错，理解上忽略就好
		}

		System.out.println("main end");

	}
}
