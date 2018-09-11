package com.smxknife.rxjava2.demo04;

import io.reactivex.Observable;
import lombok.extern.java.Log;

/**
 * @author smxknife
 * 2018/9/11
 */
@Log
public class JustDemo {
	public static void main(String[] args) {
		justTest();
	}

	private static void justTest() {
		// just的参数最多10个
//		Observable<String> just = Observable.just("nihao", "test", "nextmsg");
//		just.subscribe(msg -> log.info(msg));

		// just support Flowable, Observable, Maybe, Single
		Observable.just("nihao", "test", "nextmsg").subscribe(log::info);

		log.info("==========================");

		Observable.just("1", "2", "3", "4").subscribe(item -> {
			log.info(() -> "onNext");
			String temp = item += "_next_handler";
			log.info(() -> temp);
		}, err -> {
			log.info(() -> "onError");
			log.info(() -> err.getClass().toString());
		}, () -> {
			log.info(() -> "onComplete");
			log.info(() -> "success");
		}, disposable -> {
			log.info(() -> "onSubscribe");
			log.info(() -> disposable.toString());
			log.info(() -> String.valueOf(disposable.isDisposed()));
		});
	}
}
