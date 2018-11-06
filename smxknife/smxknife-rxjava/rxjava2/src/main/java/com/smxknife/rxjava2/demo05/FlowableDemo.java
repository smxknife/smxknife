package com.smxknife.rxjava2.demo05;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.java.Log;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * @author smxknife
 * 2018/9/12
 */
@Log(topic = "Flowable")
public class FlowableDemo {
	public static void main(String[] args) {
		Flowable<Integer> upStream = Flowable.create(new FlowableOnSubscribe<Integer>() {
			@Override
			public void subscribe(FlowableEmitter<Integer> flowableEmitter) throws Exception {
				for (int i = 0; i < 129; i++) {
					System.out.println("Thread-ID=" + Thread.currentThread().getId() + " | emit " + i);
					flowableEmitter.onNext(i);

				}
				flowableEmitter.onComplete();
			}
		}, BackpressureStrategy.ERROR);

		Subscriber<Integer> downStream = new Subscriber<Integer>() {

			@Override
			public void onSubscribe(Subscription subscription) {
				log.info("onSubscribe " + subscription.toString());
				subscription.request(Long.MAX_VALUE);
			}

			@Override
			public void onNext(Integer integer) {
				System.out.println("Thread-ID=" + Thread.currentThread().getId() + " | onNext " + integer);
			}

			@Override
			public void onError(Throwable throwable) {
				log.severe(throwable.getMessage());
			}

			@Override
			public void onComplete() {
				log.info("onComplete");
			}
		};

		upStream
				.subscribeOn(Schedulers.io())
				.observeOn(Schedulers.single())
				.subscribe(downStream);

	}
}
