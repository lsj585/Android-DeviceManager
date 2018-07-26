package com.nd.adhoc.dmsdk.demo.presenter.strategy.device;

import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.demo.model.impl.DeviceControlModel;
import com.nd.adhoc.dmsdk.demo.presenter.strategy.DeviceStrategy;
import com.nd.adhoc.dmsdk.demo.presenter.annotation.AppStategyKey;
import com.nd.adhoc.dmsdk.demo.presenter.factory.DeviceManagerFactory;
import com.nd.adhoc.dmsdk.demo.utils.RxJavaUtils;
import com.nd.adhoc.dmsdk.demo.view.DeviceControlView;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
@Service(DeviceStrategy.class)
@AppStategyKey(DeviceManagerFactory.STRATEGY_DEVICE_GETLIST)
public class DeviceStrategy_GetList implements DeviceStrategy {

    private Subscription mSunscription;

    @Override
    public void execute(int position, @NonNull final DeviceControlView view, @NonNull final DeviceControlModel model) {

        if (mSunscription != null && RxJavaUtils.isSubscribed(mSunscription)) {
            RxJavaUtils.doUnsubscribe(mSunscription);
        }

        mSunscription = Observable.create(new Observable.OnSubscribe<List>() {
            @Override
            public void call(Subscriber<? super List> subscriber) {
                List list=model.getList();
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        }).compose(RxJavaUtils.<List>applyDefaultSchedulers()).subscribe(new Subscriber<List>() {

            @Override
            public void onCompleted() {
                RxJavaUtils.doUnsubscribe(this);
            }

            @Override
            public void onError(Throwable throwable) {
                RxJavaUtils.doUnsubscribe(this);
            }

            @Override
            public void onNext(List list) {
                view.showList(list);
                RxJavaUtils.doUnsubscribe(this);
            }
        });

    }
}
