package com.smxknife.rxjava2.demo04;

import io.reactivex.Observable;

/**
 * @author smxknife
 * 2018/9/11
 */
public class ReduceDemo {
	public static void main(String[] args) {
		// reduce support Flowable, Observable
		reduceTest();
	}

	private static void reduceTest() {
		Observable<Integer> range = Observable.range(1, 5);
		range.subscribe(System.out::println);
		range.reduce((x, y) -> {
//			System.out.println(x);
//			System.out.println(y);
			return x * y;
		}).subscribe(System.out::println);
	}
}
