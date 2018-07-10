package com.smxknife.rxjava2.demo01;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class Hello {
	public static void main(String[] args) throws InterruptedException {
		Flowable.just("hello, world").subscribe(System.out::println);

		Flowable<Integer> flow = Flowable.range(1, 5).map(v -> v * v).filter(v -> v % 3 == 0);

		Observable.create(emitter -> {
			while (!emitter.isDisposed()) {
				long time = System.currentTimeMillis();
				emitter.onNext(time);
				if (time % 2 != 0) {
					emitter.onError(new IllegalStateException("Odd  millisecond"));
					break;
				}
			}
		}).subscribe(System.out::println, Throwable::printStackTrace);

		Flowable.fromCallable(() -> {
			Thread.sleep(1000);
			return "Done";
		})
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.single())
				.subscribe(System.out::println, Throwable::printStackTrace);

		Thread.sleep(2000);

		Flowable.range(1, 10)
				.observeOn(Schedulers.computation())
				.map(v -> v * v)
				.blockingSubscribe(System.out::println);
		System.out.println("======================");
		Flowable.range(1, 10)
				.observeOn(Schedulers.computation())
				.map(v -> v * v)
				.subscribe(System.out::println);

		Thread.sleep(2000);
		System.out.println("======================");

		Flowable.range(1, 10)
				.flatMap(v ->
					Flowable.just(v)
							.subscribeOn(Schedulers.computation())
							.map(w -> w * w)
				).blockingSubscribe(System.out::println);

	}
}
