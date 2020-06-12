package com.smxknife.rxjava2.demo06;


import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author smxknife
 * 2020/5/15
 */
public class JustDo {
	public static void main(String[] args) {

		Observable.just("Hello world")
				.doOnSubscribe(new Consumer<Disposable>() {
					@Override
					public void accept(Disposable disposable) throws Exception {
						Log.log("doOnSubscribe | " + disposable.toString());
					}
				})
				.doOnNext(new Consumer<String>() {
					@Override
					public void accept(String s) throws Exception {
						Log.log("doOnNext | " + s);
					}
				})
				.doAfterNext(new Consumer<String>() {
					@Override
					public void accept(String s) throws Exception {
						Log.log("doAfterNext | " + s);
					}
				})
				.doAfterTerminate(new Action() {
					@Override
					public void run() throws Exception {
						Log.log("doAfterTerminate | ");
					}
				})
				.doFinally(new Action() {
					@Override
					public void run() throws Exception {
						Log.log("doFinally");
					}
				})
				.doOnComplete(new Action() {
					@Override
					public void run() throws Exception {
						Log.log("doOnComplete");
					}
				})
				.doOnDispose(new Action() {
					@Override
					public void run() throws Exception {
						Log.log("doOnDispose");
					}
				})
				.doOnEach(new Observer<String>() {
					@Override
					public void onSubscribe(Disposable disposable) {
						Log.log("doOnEach onSubscribe | " + disposable);
					}

					@Override
					public void onNext(String s) {
						Log.log("doOnEach onNext | " + s);
					}

					@Override
					public void onError(Throwable throwable) {
						Log.log("doOnEach onError | " + throwable.getMessage());
					}

					@Override
					public void onComplete() {
						Log.log("doOnEach onComplete");
					}
				})
				.doOnEach(new Consumer<Notification<String>>() {
					@Override
					public void accept(Notification<String> stringNotification) throws Exception {
						Log.log("doOnEach Consumer | " + stringNotification.getValue());
					}
				})
				.doOnLifecycle(new Consumer<Disposable>() {
					@Override
					public void accept(Disposable disposable) throws Exception {
						Log.log("doOnLifecycle accept | " + disposable);
					}
				}, new Action() {
					@Override
					public void run() throws Exception {
						Log.log("doOnLifecycle Action");
					}
				})
				.subscribe(new Consumer<String>() {
					@Override
					public void accept(String s) throws Exception {
						Log.log("onNext | ");
					}
				}, new Consumer<Throwable>() {
					@Override
					public void accept(Throwable throwable) throws Exception {
						Log.log("onError | ");
					}
				}, new Action() {
					@Override
					public void run() throws Exception {
						Log.log("onComplete | ");
					}
				}, new Consumer<Disposable>() {
					@Override
					public void accept(Disposable disposable) throws Exception {
						Log.log("onSubscribe | ");
					}
				});

	}


}
