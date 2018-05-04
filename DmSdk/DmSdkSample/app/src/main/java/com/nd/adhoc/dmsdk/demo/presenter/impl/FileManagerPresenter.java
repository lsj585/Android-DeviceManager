package com.nd.adhoc.dmsdk.demo.presenter.impl;

import android.content.Context;
import android.util.Log;

import com.nd.adhoc.dmsdk.demo.model.impl.DeviceControlModel;
import com.nd.adhoc.dmsdk.demo.model.impl.FileManagerModel;
import com.nd.adhoc.dmsdk.demo.presenter.BasePresenter;
import com.nd.adhoc.dmsdk.demo.presenter.IDeviceControlPresenter;
import com.nd.adhoc.dmsdk.demo.presenter.IFileManagerPresenter;
import com.nd.adhoc.dmsdk.demo.utils.RxJavaUtils;
import com.nd.adhoc.dmsdk.demo.view.DeviceControlView;
import com.nd.adhoc.dmsdk.demo.view.FileManagerView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class FileManagerPresenter extends BasePresenter<FileManagerView,FileManagerModel> implements IFileManagerPresenter {


    public FileManagerPresenter(Context context, FileManagerView view) {
        super(context, view);
    }

    @Override
    public void getFileAppList() {
        Log.i(this.getClass().getName(),"getFileAppList");
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
                view.showList(list);
            }
        });
    }

    @Override
    public void onClick(int dialogPos,int viewPosition) {
        switch (dialogPos){
            case 0:
                installApp(viewPosition);
                break;
            case 1:
                break;
        }
    }

    private void installApp(final int viewPosition){

        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean isIntsall=false;
                try{
                    isIntsall=modle.install(viewPosition);
                }catch (Exception e){
                    e.printStackTrace();
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

            }

            @Override
            public void onNext(Boolean isSuccess) {
                if(isSuccess){
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
