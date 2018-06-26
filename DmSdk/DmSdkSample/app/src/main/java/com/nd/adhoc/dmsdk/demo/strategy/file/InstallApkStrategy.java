package com.nd.adhoc.dmsdk.demo.strategy.file;

import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.demo.model.impl.FileManagerModel;
import com.nd.adhoc.dmsdk.demo.strategy.FileControlStrategy;
import com.nd.adhoc.dmsdk.demo.utils.RxJavaUtils;
import com.nd.adhoc.dmsdk.demo.view.FileManagerView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * 安装APP
 */
public class InstallApkStrategy implements FileControlStrategy {

    private Subscription mSubscription;
    @Override
    public void execute(final int position, @NonNull final FileManagerView view, @NonNull final FileManagerModel model) {

        RxJavaUtils.doUnsubscribe(mSubscription);
        mSubscription= Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean isIntsall=false;
                try{
                    isIntsall=model.install(position);
                }catch (Exception e){
                    subscriber.onError(e);
                }
                subscriber.onNext(isIntsall);
                subscriber.onCompleted();
            }
        }).compose(RxJavaUtils.<Boolean>applyDefaultSchedulers()).subscribe(new Subscriber<Boolean>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(Boolean isSuccess) {
                if(isSuccess){
                    view.updateView(position);
                }
            }
        });
    }
}
