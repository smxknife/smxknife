package com.smxknife.rxjava2.demo03;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import java.util.Arrays;
import java.util.List;

public class ObservableDemo {
	public static void main(String[] args) {

		List<String> strings = Arrays.asList("str1", "str2", "str3");

		Observable.<String>create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> emitter) throws Exception {
				strings.stream().peek(str -> {
					System.out.println("log: " + str);
				}).forEach(str -> {
					emitter.onNext(str);
				});
				emitter.onComplete();
			}
		});

		Observable.<String>create(emitter -> {
			strings.forEach(str -> {
				emitter.onNext(str.toUpperCase());
			});
			emitter.onComplete();
		});
	}
}
