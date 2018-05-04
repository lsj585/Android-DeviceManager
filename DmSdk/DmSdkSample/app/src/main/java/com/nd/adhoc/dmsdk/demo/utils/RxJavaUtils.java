package com.nd.adhoc.dmsdk.demo.utils;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaUtils {

    public RxJavaUtils() {

    }

    public static <T> Observable.Transformer<T, T> applyDefaultSchedulers() {
        return new Observable.Transformer<T, T>() {
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static boolean isSubscribed(Subscription subscription) {
        return subscription != null && !subscription.isUnsubscribed();
    }

    public static void doUnsubscribe(Subscription subscription) {
        if (isSubscribed(subscription)) {
            subscription.unsubscribe();
        }

    }

    public static <T> void safeSubscribe(Observable<T> observable) {
        if (observable != null) {
            observable.subscribe(new Subscriber<T>() {
                public void onCompleted() {
                }

                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                public void onNext(Object o) {
                }
            });
        }
    }
}
