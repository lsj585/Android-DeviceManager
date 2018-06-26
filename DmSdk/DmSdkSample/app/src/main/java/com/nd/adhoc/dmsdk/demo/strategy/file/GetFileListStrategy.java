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
 * 获取本地未安装的APP列表
 */
public class GetFileListStrategy implements FileControlStrategy {

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
