package com.smxknife.rxjava2.demo04;

import io.reactivex.Observable;

import java.io.IOException;

/**
 * @author smxknife
 * 2018/9/11
 */
public class ErrorDemo {
	public static void main(String[] args) {
		// error support all Flowable, Observable, Maybe, Single, Completable
//		errorTest();

		errorConditionTest();
	}

	private static void errorConditionTest() {
		Observable<String> observable = Observable.fromCallable(() -> {
			if (Math.random() < 0.5) {
				throw new IOException();
			}
			throw new IllegalArgumentException();
		});

		Observable<String> result = observable.onErrorResumeNext(error -> {
			if (error instanceof IllegalArgumentException) {
				return Observable.empty();
			}
			return Observable.error(error);
		});

		for (int i = 0; i < 10; i++) {
			result.subscribe(
					v -> System.out.println("This should never be printed!"),
					error -> error.printStackTrace(),
					() -> System.out.println("Done"));
		}
	}

	private static void errorTest() {
		Observable<String> observable = Observable.error(new IOException());

		observable.subscribe(
				v -> System.out.println("This should never be printed!"),
				error -> error.printStackTrace(),
				() -> System.out.println("This neither!"));
	}
}
