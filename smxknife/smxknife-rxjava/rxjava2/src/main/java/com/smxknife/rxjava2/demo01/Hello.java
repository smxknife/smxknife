package com.smxknife.rxjava2.demo01;

import io.reactivex.Flowable;

public class Hello {
	public static void main(String[] args) {
		Flowable.just("hello, world").subscribe(System.out::println);
	}
}
