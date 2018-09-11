package com.smxknife.rxjava2.demo04;

import io.reactivex.Observable;

/**
 * @author smxknife
 * 2018/9/11
 */
public class NeverDemo {
	public static void main(String[] args) {
		// never support Flowable, Observable, Maybe, Single, Completable
		neverTest();
	}

	private static void neverTest() {
		Observable<String> never = Observable.never();

		never.subscribe(
				v -> System.out.println("This should never be printed!"),
				error -> System.out.println("Or this!"),
				() -> System.out.println("This neither!"));
	}
}
