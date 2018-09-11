package com.smxknife.rxjava2.demo04;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2018/9/11
 */
public class TimerDemo {
	public static void main(String[] args) {
		// timer support all Flowable, Observable, Maybe, Single, Completable
		timerTest();
	}

	private static void timerTest() {
		Observable<Long> eggTimer = Observable.timer(2, TimeUnit.SECONDS);

		eggTimer.blockingSubscribe(v -> System.out.println("Egg is ready!"));
	}
}
