package com.smxknife.rxjava2.demo04;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2018/9/11
 */
public class IntervalDemo {
	public static void main(String[] args) {
		// interval support Flowable, Observable
//		intervalTest();
		intervalTest1();
	}

	private static void intervalTest1() {
		Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS);
		interval.subscribe(time -> {
			System.out.println(time);
			if (time % 2 == 0) {
				System.out.println("Tick");
			} else {
				System.out.println("Tock");
			}
		});
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void intervalTest() {
		// 参考https://github.com/ReactiveX/RxJava/issues/4132
		// 注意Schedulers.trampoline()
		// 如果不加的话，默认是在不同的线程中执行，主线程执行完毕之后，subscriber还没机会执行，所以没有任何输出
		Observable<Long> interval = Observable.interval(1, TimeUnit.SECONDS, Schedulers.trampoline());
		interval.subscribe(time -> {
			System.out.println(time);
			if (time % 2 == 0) {
				System.out.println("Tick");
			} else {
				System.out.println("Tock");
			}
		});
	}
}
