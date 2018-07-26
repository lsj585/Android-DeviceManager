package com.nd.adhoc.dmsdk.demo.presenter.strategy.file;

import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.demo.model.impl.FileManagerModel;
import com.nd.adhoc.dmsdk.demo.presenter.annotation.AppStategyKey;
import com.nd.adhoc.dmsdk.demo.presenter.annotation.FileControlStategyKey;
import com.nd.adhoc.dmsdk.demo.presenter.factory.ApkFireFactory;
import com.nd.adhoc.dmsdk.demo.presenter.factory.AppManagerFactory;
import com.nd.adhoc.dmsdk.demo.presenter.strategy.AppStrategy;
import com.nd.adhoc.dmsdk.demo.presenter.strategy.FileControlStrategy;
import com.nd.adhoc.dmsdk.demo.utils.RxJavaUtils;
import com.nd.adhoc.dmsdk.demo.view.FileManagerView;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * 获取本地未安装的APP列表
 */
@Service(FileControlStrategy.class)
@FileControlStategyKey(ApkFireFactory.STRATEGY_FILE_GETLIST)
public class FileStrategy_GetFileList implements FileControlStrategy {

    private Subscription mSubscription;
    @Override
    public void execute(int position, @NonNull final FileManagerView view, @NonNull final FileManagerModel model) {

        RxJavaUtils.doUnsubscribe(mSubscription);

        mSubscription= Observable.create(new Observable.OnSubscribe<List>() {
            @Override
            public void call(Subscriber<? super List> subscriber) {
                List list=model.getList();
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        }).compose(RxJavaUtils.<List>applyDefaultSchedulers()).subscribe(new Subscriber<List>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(List list) {
                view.showList(list);
            }
        });
    }
}
