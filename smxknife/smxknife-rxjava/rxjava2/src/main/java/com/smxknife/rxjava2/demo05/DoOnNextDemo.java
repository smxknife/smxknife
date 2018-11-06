package com.smxknife.rxjava2.demo05;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author smxknife
 * 2018/9/13
 */
public class DoOnNextDemo {
	public static void main(String[] args) {
		Observable.just(1)
				.doOnNext(item -> {
					System.out.println("doOnNext: 1 --> " + item);
				})
				.doOnNext(item -> {
					System.out.println("doOnNext: 2 --> " + item);
				})
				.doAfterNext(item -> {
					System.out.println("doAfterNext: 1 --> " + item);
				})
				.doAfterNext(item -> {
					System.out.println("doAfterNext: 2 --> " + item);
				})
				.doOnComplete(() -> {
					System.out.println("doOnComplete");
				})
				.doAfterTerminate(() -> {
					System.out.println("doAfterTerminate");
				})
				.doOnSubscribe(item -> {
					System.out.println("doOnSubscribe" + item);
				})
				.doFinally(() -> {
					System.out.println("doOnFinally");
				})
				.doOnDispose(() -> {
					System.out.println("doOnDispose");
				})
				.doOnError(error ->{
					System.out.println("doOnError");
				})
				.doOnLifecycle(item -> {
					System.out.println("doOnLifecycle " + item);
				}, () -> {
					System.out.println("doOnLifeCycle");
				})
				.doOnEach(item -> {
					System.out.println("doOnEach " + item);
				})
				.observeOn(Schedulers.computation())
				.subscribe();
	}
}
