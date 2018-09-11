package com.smxknife.rxjava2.demo04;

import io.reactivex.Observable;

import java.util.concurrent.Callable;

/**
 * @author smxknife
 * 2018/9/11
 */
public class DeferDemo {
	public static void main(String[] args) {
		// defer support all Flowable, Observable, Maybe, Single, Completable
		deferTest();
	}

	private static void deferTest() {
		// 直到有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable
		Callable callable = () -> {
			System.out.println(">> ****");
			Observable<String> just = Observable.just("1", "2", "3");
			return just;
		};

		Observable.defer(callable).subscribe(System.out::println);


	}
}
