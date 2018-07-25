package com.nd.adhoc.dmsdk.demo.strategy.app;

import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.demo.model.impl.AppListManagerModel;
import com.nd.adhoc.dmsdk.demo.strategy.ApplicationStrategy;
import com.nd.adhoc.dmsdk.demo.utils.RxJavaUtils;
import com.nd.adhoc.dmsdk.demo.view.AppManagerView;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * 策略类
 */
public class AllowWipeDataApplicationApplicationStrategy implements ApplicationStrategy {

    private Subscription mSunscription;

    @Override
    public void execute(final int position, @NonNull final AppManagerView view, @NonNull final AppListManagerModel model) {

        if (mSunscription != null && RxJavaUtils.isSubscribed(mSunscription)) {
            RxJavaUtils.doUnsubscribe(mSunscription);
        }
        mSunscription = Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(model.allowClearData(position));
                subscriber.onCompleted();
            }
        }).compose(RxJavaUtils.<Boolean>applyDefaultSchedulers()).subscribe(new Subscriber<Boolean>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                RxJavaUtils.doUnsubscribe(this);
            }

            @Override
            public void onNext(Boolean success) {

                if(view == null){
                    return;
                }
                if (success) {
                    model.updateWipeStatus(position);
                    view.updateView(position);
                } else {
                    view.updateMsg("该应用不允许被用户清理数据");
                }
                RxJavaUtils.doUnsubscribe(this);
            }
        });
    }
}
