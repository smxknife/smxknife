package com.smxknife.cloud.netflix.user.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author smxknife
 * 2021/5/2
 */
public class OnlyHystrixWithoutAnythingTest extends HystrixCommand {

	protected OnlyHystrixWithoutAnythingTest(HystrixCommandGroupKey group) {
		super(group);
	}

	@Override
	protected Object run() throws Exception {
		// 相当于自定义实现的try方法
		System.out.println("run ......");
		int i = 1/0;
		System.out.println("run normal...");
		return null;
	}

	@Override
	protected Object getFallback() {
		// 相当于自定义实现的catch方法
		System.out.println("getFallback ......");
		return super.getFallback();
	}

	public static void main(String[] args) {
		//	OnlyHystrixWithoutAnythingTest hystrixTest = new OnlyHystrixWithoutAnythingTest(HystrixCommandGroupKey.Factory.asKey("ext"));
		/**
		 * execute()：以同步阻塞方式执行run()。以demo为例，调用execute()后，
		 * hystrix先创建一个新线程运行run()，
		 * 	接着调用程序要在execute()调用处一直阻塞着，直到run()运行完成
		 */
		//	System.out.println("result:" + hystrixTest.execute());

		/**
		 * queue()：以异步非阻塞方式执行run()。以demo为例，
		 * 	一调用queue()就直接返回一个Future对象，
		 * 	同时hystrix创建一个新线程运行run()，
		 * 	调用程序通过Future.get()拿到run()的返回结果，
		 * 	而Future.get()是阻塞执行的
		 */
		Future<String> futureResult = new OnlyHystrixWithoutAnythingTest(HystrixCommandGroupKey.Factory.asKey("ext")).queue();
		String result = "";
		try {
			result = futureResult.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("程序结果："+result);
	}
}
