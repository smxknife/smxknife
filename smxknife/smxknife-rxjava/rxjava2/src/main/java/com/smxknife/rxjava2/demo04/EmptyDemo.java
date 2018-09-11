package com.smxknife.rxjava2.demo04;

import io.reactivex.Observable;

/**
 * @author smxknife
 * 2018/9/11
 */
public class EmptyDemo {
	public static void main(String[] args) {
		// empty support Flowable, Observable, Maybe, Completable
		emptyTest();
	}

	private static void emptyTest() {
		Observable<String> empty = Observable.empty();

		empty.subscribe(
				v -> System.out.println("This should never be printed!"),
				error -> System.out.println("Or this!"),
				() -> System.out.println("Done will be printed."));
	}
}
