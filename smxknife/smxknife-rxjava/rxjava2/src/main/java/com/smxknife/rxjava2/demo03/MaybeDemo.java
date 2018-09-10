package com.smxknife.rxjava2.demo03;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.disposables.Disposable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaybeDemo {

	static List<String> strings = new ArrayList<>();

	public static void main(String[] args) {
		strings.addAll(Arrays.asList("demo1", "demo2"));
//		strings.add(null);

		Maybe.create(new MaybeOnSubscribe<String>() {
			@Override
			public void subscribe(MaybeEmitter<String> maybeEmitter) throws Exception {
				strings.forEach(str -> maybeEmitter.onSuccess(str));
//				maybeEmitter.onError(new Throwable("xxxxxx"));
//				maybeEmitter.onComplete();
			}
		}).subscribe(new MaybeObserver<String>() {
			@Override
			public void onSubscribe(Disposable disposable) {
				System.out.println("onSubscribe");
			}

			@Override
			public void onSuccess(String s) {
				System.out.println("onSuccess: " + s);
			}

			@Override
			public void onError(Throwable throwable) {
				System.out.println("onError: " + throwable.getMessage());
			}

			@Override
			public void onComplete() {
				System.out.println("onComplete");
			}
		});

		Maybe.create(maybeEmitter -> {
			strings.forEach(str -> maybeEmitter.onSuccess(str));
			maybeEmitter.onError(new Throwable("xxxxxx"));
			maybeEmitter.onComplete();
		});
	}
}
