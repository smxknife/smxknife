package com.smxknife.rxjava2.demo03;

import io.reactivex.Observable;

public class ExDemo01 {

	static String result = "";

	public static void main(String[] args) {

		Observable<String> observable = Observable.just("Hello");

		observable.subscribe(s -> result = s);

		System.out.println("result: " + result);
	}
}
