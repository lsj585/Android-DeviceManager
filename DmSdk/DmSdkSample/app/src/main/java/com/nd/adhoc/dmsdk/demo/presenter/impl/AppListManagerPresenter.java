package com.nd.adhoc.dmsdk.demo.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.nd.adhoc.dmsdk.demo.model.impl.AppListManagerModel;
import com.nd.adhoc.dmsdk.demo.presenter.BasePresenter;
import com.nd.adhoc.dmsdk.demo.presenter.IAppManagerPresenter;
import com.nd.adhoc.dmsdk.demo.utils.RxJavaUtils;
import com.nd.adhoc.dmsdk.demo.view.AppManagerView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class AppListManagerPresenter extends BasePresenter<AppManagerView,AppListManagerModel> implements IAppManagerPresenter {


    public AppListManagerPresenter(Context context, AppManagerView view) {
        super(context, view);
    }
    @Override
    public void getApplist() {

        Observable.create(new Observable.OnSubscribe<List>() {
            @Override
            public void call(Subscriber<? super List> subscriber) {
                List list=modle.getList();
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        }).compose(RxJavaUtils.<List>applyDefaultSchedulers()).subscribe(new Subscriber<List>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(List list) {
                Log.i(this.getClass().getName(),String.format("showList:%d", list.size()));
                view.showList(list);
            }
        });
    }

    @Override
    public void onClick(int dialogPos,int viewPosition) {
        switch (dialogPos){
            case 0:
                //卸载
                break;
            case 1:
                //阻止卸载
                break;
            case 2:
                //清除应用数据
                break;
            case 3:
                //加入白名单
                break;
            case 4:
                //加入黑名单
                break;
            case 5:
                //启动应用
                startApp(viewPosition);
                break;
            case 6:
                //关闭应用
                stopApp(viewPosition);
                break;

        }

    }

    private void stopApp(final int viewPosition) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(modle.stopApp(viewPosition));
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
                    modle.update(viewPosition,true);
                    view.updateView(viewPosition);
                }
            }
        });
    }

    /**
     * 启动app
     * @param viewPosition
     */
    private void startApp(final int viewPosition){
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(modle.startApp(viewPosition));
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
                    modle.update(viewPosition,success);
                    view.updateView(viewPosition);
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        modle=null;
        super.onDestroy();
    }
}
