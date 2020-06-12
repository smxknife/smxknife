package com.smxknife.rxjava2.demo06;


import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author smxknife
 * 2020/5/15
 */
public class Just {
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			Observable.just("Hello world").subscribe(subscribe -> {
				System.out.print("onNext | ");
			}, onError -> {
				System.out.print("onError | ");
			}, () -> {
				System.out.print("onComplete | ");
			}, onSubscribe -> {
				System.out.print("onSubscribe | ");
			});
			System.out.println();
		}

		Observable.just("Hello world").subscribe(new Consumer<String>() {
			@Override
			public void accept(String s) throws Exception {
				System.out.print("onNext | ");
			}
		}, new Consumer<Throwable>() {
			@Override
			public void accept(Throwable throwable) throws Exception {
				System.out.print("onError | ");
			}
		}, new Action() {
			@Override
			public void run() throws Exception {
				System.out.print("onComplete | ");
			}
		}, new Consumer<Disposable>() {
			@Override
			public void accept(Disposable disposable) throws Exception {
				System.out.print("onSubscribe | ");
			}
		});

	}
}
