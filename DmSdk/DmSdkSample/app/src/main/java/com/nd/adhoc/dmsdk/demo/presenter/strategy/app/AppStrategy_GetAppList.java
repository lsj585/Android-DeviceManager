package com.nd.adhoc.dmsdk.demo.presenter.strategy.app;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nd.adhoc.dmsdk.demo.model.impl.AppListManagerModel;
import com.nd.adhoc.dmsdk.demo.presenter.factory.AppManagerFactory;
import com.nd.adhoc.dmsdk.demo.presenter.strategy.AppStrategy;
import com.nd.adhoc.dmsdk.demo.presenter.annotation.AppStategyKey;
import com.nd.adhoc.dmsdk.demo.utils.RxJavaUtils;
import com.nd.adhoc.dmsdk.demo.view.AppManagerView;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.List;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * 获取应用列表
 */
@Service(AppStrategy.class)
@AppStategyKey(AppManagerFactory.STRATEGY_GET_APPLIST)
public class AppStrategy_GetAppList implements AppStrategy {

    private Subscription mSunscription;

    @Override
    public void execute(final int position, @NonNull final AppManagerView view, @NonNull final AppListManagerModel model) {

        if (mSunscription != null && RxJavaUtils.isSubscribed(mSunscription)) {
            RxJavaUtils.doUnsubscribe(mSunscription);
        }
        mSunscription=Observable.create(new Observable.OnSubscribe<List>() {
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
                Log.i(this.getClass().getName(),String.format("showList:%d", list.size()));
                view.showList(list);
                RxJavaUtils.doUnsubscribe(this);
            }
        });
    }
}
