package com.smxknife.rxjava2.demo04;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2018/9/11
 */
public class CreateDemo {
	public static void main(String[] args) throws InterruptedException {
		// create support all Flowable, Observable, Maybe, Single, Completable
		createTest();

	}

	private static void createTest() throws InterruptedException {
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

		ObservableOnSubscribe<String> handler = emitter -> {
			System.out.println(">> xxxxx");
			Future<Object> future = executor.schedule(() -> {
				emitter.onNext("hello");
				emitter.onNext("world");
				emitter.onNext("test");
				emitter.onComplete();
				return null;
			}, 1, TimeUnit.SECONDS);

			emitter.setCancellable(() -> future.cancel(false));
		};

		Observable<String> observable = Observable.create(handler);
		observable.subscribe(item -> {
			System.out.println(item);
		}, error -> {
			error.printStackTrace();
		}, () -> {
			System.out.println("complete");
		});

		Thread.sleep(2000);

		executor.shutdown();
	}
}
