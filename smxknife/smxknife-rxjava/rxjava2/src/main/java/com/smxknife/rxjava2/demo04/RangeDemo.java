package com.smxknife.rxjava2.demo04;

import io.reactivex.Observable;

/**
 * @author smxknife
 * 2018/9/11
 */
public class RangeDemo {
	public static void main(String[] args) {
		// range support Flowable, Observable
		rangeTest();
	}

	private static void rangeTest() {
		String greeting = "hello new world";
		Observable<Integer> range = Observable.range(1, greeting.length() - 2);
		range.subscribe(System.out::println);
		Observable<Character> map = range.map(idx -> greeting.charAt(idx));
		map.subscribe(System.out::println);
	}
}
