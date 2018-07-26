package com.nd.adhoc.dmsdk.demo.presenter.strategy.file;

import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.demo.model.impl.FileManagerModel;
import com.nd.adhoc.dmsdk.demo.presenter.annotation.FileControlStategyKey;
import com.nd.adhoc.dmsdk.demo.presenter.factory.ApkFireFactory;
import com.nd.adhoc.dmsdk.demo.presenter.strategy.FileControlStrategy;
import com.nd.adhoc.dmsdk.demo.utils.RxJavaUtils;
import com.nd.adhoc.dmsdk.demo.view.FileManagerView;
import com.nd.sdp.android.serviceloader.annotation.Service;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * 安装APP
 */
@Service(FileControlStrategy.class)
@FileControlStategyKey(ApkFireFactory.STRATEGY_FILE_INSTALLAPK)
public class FileStrategy_InstallApk implements FileControlStrategy {

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
