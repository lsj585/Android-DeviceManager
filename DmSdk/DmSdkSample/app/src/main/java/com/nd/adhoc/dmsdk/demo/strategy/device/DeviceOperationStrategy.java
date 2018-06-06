package com.nd.adhoc.dmsdk.demo.strategy.device;

import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.demo.model.impl.DeviceControlModel;
import com.nd.adhoc.dmsdk.demo.strategy.DeviceStrategy;
import com.nd.adhoc.dmsdk.demo.utils.RxJavaUtils;
import com.nd.adhoc.dmsdk.demo.view.DeviceControlView;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

public class DeviceOperationStrategy implements DeviceStrategy {

    private Subscription mSunscription;

    @Override
    public void execute(final int position, @NonNull final DeviceControlView view, @NonNull final DeviceControlModel model) {

        if(position==8){
            model.update(position,true);
            view.updateView(position);
            view.jumpHome();
            return;
        }

        if (mSunscription != null && RxJavaUtils.isSubscribed(mSunscription)) {
            RxJavaUtils.doUnsubscribe(mSunscription);
        }

        mSunscription = Observable.create(new Observable.OnSubscribe<Boolean>() {
                    @Override
                    public void call(Subscriber<? super Boolean> subscriber) {
                        try {
                            subscriber.onNext(model.updateStatus(position));
                        } catch (Exception e) {
                            e.printStackTrace();
                            view.updateMsg("该功能不支持");
                        }
                        subscriber.onCompleted();
                    }
                }).compose(RxJavaUtils.<Boolean>applyDefaultSchedulers()).subscribe(new Subscriber<Boolean>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onNext(Boolean success) {
                        if(success) {
                            model.update(position,true);
                            view.updateView(position);
                        }
                        RxJavaUtils.doUnsubscribe(this);
                    }
                });
    }
}
