package com.nd.adhoc.dmsdk.demo.presenter.strategy.app;

import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.demo.model.impl.AppListManagerModel;
import com.nd.adhoc.dmsdk.demo.presenter.factory.AppManagerFactory;
import com.nd.adhoc.dmsdk.demo.presenter.strategy.AppStrategy;
import com.nd.adhoc.dmsdk.demo.presenter.annotation.AppStategyKey;
import com.nd.adhoc.dmsdk.demo.utils.RxJavaUtils;
import com.nd.adhoc.dmsdk.demo.view.AppManagerView;
import com.nd.sdp.android.serviceloader.annotation.Service;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * 卸载当前的APP
 */
@Service(AppStrategy.class)
@AppStategyKey(AppManagerFactory.STRATEGY_UNINSTALL)
public class AppStrategy_Uninstall implements AppStrategy {

    private Subscription mSunscription;

    @Override
    public void execute(final int position, @NonNull final AppManagerView view, @NonNull final AppListManagerModel model) {

        if(mSunscription!= null && RxJavaUtils.isSubscribed(mSunscription)){
            RxJavaUtils.doUnsubscribe(mSunscription);
        }
        mSunscription=Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(model.uninstallApp(position));
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
                if(success) {
                    model.delete(position);
                    view.removeUpdate(position);
                }else{
                    view.updateMsg("该应用不允许被用户卸载");
                }
                RxJavaUtils.doUnsubscribe(this);
            }
        });
    }
}
