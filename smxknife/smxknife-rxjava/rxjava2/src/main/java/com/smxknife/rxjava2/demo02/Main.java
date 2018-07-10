package com.smxknife.rxjava2.demo02;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Main {

    static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) {

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> observableEmitter) throws Exception {
                builder.append("Observable emit 1" + "\n");
                log("Observable emit 1" + "\n");
                observableEmitter.onNext(1);


                builder.append("Observable emit 2" + "\n");
                log("Observable emit 2" + "\n");
                observableEmitter.onNext(2);

                builder.append("Observable emit 3" + "\n");
                log("Observable emit 3" + "\n");
                observableEmitter.onNext(3);

                builder.append("Observable emit 4" + "\n");
                log("Observable emit 4" + "\n");
                observableEmitter.onNext(4);
                observableEmitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {

            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable disposable) {
                builder.append("onSubscribe: " + disposable.isDisposed() + "\n");
                log("onSubscribe: " + disposable.isDisposed() + "\n");
                mDisposable = disposable;
            }

            @Override
            public void onNext(Integer integer) {
                builder.append("onNext : value : " + integer + "\n");
                log("onNext : value : " + integer + "\n");
                i++;
                if (i == 2) {
                    mDisposable.dispose();
                    builder.append("onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                    log("onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                }
            }

            @Override
            public void onError(Throwable throwable) {
                builder.append("onError : value : " + throwable.getMessage() + "\n");
                log("onError : value : " + throwable.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                builder.append("onComplete" + "\n");
                log("onComplete" + "\n");
            }
        });
    }

    private static void log(Object log) {
        System.out.println("log: " + log);
    }
}
