package com.smxknife.deferred;

import com.stumbleupon.async.Deferred;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2019-08-14
 */
public class DeferredMain {
	public static void main(String[] args) {
		Deferred<String> deferred = new Deferred();
		deferred.addBoth(str -> {
			System.out.println(Thread.currentThread().getName() + "_1_:::" + str);
			return str + 1;
		}).addBoth(str -> {
			System.out.println(Thread.currentThread().getName() + "_2_:::" + str);
			Thread.sleep(1000);
			return str + 1;
		}).addBoth(str -> {
			System.out.println(Thread.currentThread().getName() + "_3_:::" + str);
			return str;
		}).addBothDeferring(str -> {
			System.out.println(Thread.currentThread().getName() + "_4_:::" + str);

			Deferred<String> innerDeferred = new Deferred<String>().addBoth(innerStr -> {
				System.out.println(Thread.currentThread().getName() + "_4-1_:::" + str + " \\ " + innerStr);
				return innerStr;
			});

			ExecutorService service = Executors.newSingleThreadExecutor();
			service.execute(() -> {
				System.out.println(Thread.currentThread().getName() + "=start.");
				innerDeferred.callback("nice");
			});
			service.shutdown();
			return innerDeferred;
		}).addBoth(str -> {
			System.out.println(Thread.currentThread().getName() + "_5_:::" + str);
			return str;
		}).addErrback(str -> {
			System.out.println(Thread.currentThread().getName() + "_err_" + str);
			return str;
		});

		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(() -> {
			System.out.println(Thread.currentThread().getName() + "_start.");
			deferred.callback("string test");
		});
		executorService.shutdown();
		System.out.println("shutdown");
	}
}
