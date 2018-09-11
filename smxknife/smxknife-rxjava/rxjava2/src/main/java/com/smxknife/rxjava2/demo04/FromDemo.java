package com.smxknife.rxjava2.demo04;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author smxknife
 * 2018/9/11
 */
@Log
public class FromDemo {


	public static void main(String[] args) {
		// [Flowable, Observable, Maybe, Single, Completable]

		// fromIterable support Flowable, Observable
//		fromIterableTest();

		// fromArray suopport Flowable, Observable
//		fromArrayTest();

		// fromCallable support Flowable, Observable, Maybe, Single, Completable
//		fromCallableTest();

		// fromAction support Maybe, Completable
//		fromActionTest();

		// fromRunnable support Maybe, Completable
//		fromRunnableTest();

		// fromFuture support Flowable, Observable, Maybe, Single, Completable
		fromFutureTest();
	}

	private static void fromFutureTest() {
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		Future<String> future = executor.schedule(() -> "Hello world!", 1, TimeUnit.SECONDS);
		Observable.fromFuture(future).subscribe(item -> {
			System.out.println(item);
		}, error -> {
			error.printStackTrace();
			System.out.println(error.getMessage());
		}, () -> {
			System.out.println("complete");
		});
		executor.shutdown();
	}

	private static void fromRunnableTest() {
//		Runnable runnable = () -> System.out.println("runnable");
		Runnable runnable = () -> {
			throw new RuntimeException();
//			throw new Exception(); // 不可以抛出检查性异常
		};
		Completable.fromRunnable(runnable).subscribe(() -> {
			System.out.println("onComplete");
		}, error -> {
			System.out.println(error.getMessage());
		});
	}

	private static void fromActionTest() {
//		Action action = () -> System.out.println("action");
		Action action = () -> {
//			throw new RuntimeException();
			throw new Exception("test exception");
		};
		Completable.fromAction(action).subscribe(() -> {
			System.out.println("onComplete");
		}, error -> {
			System.out.println(error.getMessage());
		});
	}

	private static void fromCallableTest() {
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "Hello Callable";
			}
		};
		Callable<String> lamCall = () -> {
			log.info("callable");
			return "World Callable";
		};

		Observable.fromCallable(lamCall).subscribe(item -> {
			System.out.println("-------");
			log.info(item.toString());
		}, error -> {
			log.info(error.getMessage());
		}, () -> {
			log.info("complete");
		});
	}

	private static void fromArrayTest() {
		Integer[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
		Observable<Integer> observable = Observable.fromArray(array);
		observable.subscribe(item -> {
			log.info(item.toString());
		}, error -> {
			log.info(error.getMessage());
		}, () -> {
			log.info("complete");
		});
	}

	private static void fromIterableTest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
		Observable<Integer> integerObservable = Observable.fromIterable(list);
		integerObservable.subscribe(item -> {
			log.info(item.toString());
		}, error -> {
			log.info(error.getMessage());
		}, () -> {
			log.info("complete");
		});
	}
}
