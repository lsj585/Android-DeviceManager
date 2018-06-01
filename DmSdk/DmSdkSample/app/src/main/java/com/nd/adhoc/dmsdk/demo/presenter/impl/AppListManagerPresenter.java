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

        RxJavaUtils.doUnsubscribe(this);
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
                uninstall(viewPosition);
                break;
            case 1:
                //阻止卸载
                forceUnIntall(viewPosition);
                break;
            case 2:
                //清除应用数据
                wipeData(viewPosition);
                break;
            case 3:
                //阻止应用清除数据
                forceClearData(viewPosition);
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
            case 7:
                //低功耗运行--进程保活
                daemno(viewPosition);
                break;
            case 8:
                //不允许运行
                unruningApp(viewPosition);
                break;
            case 9:
                allowRunningApp(viewPosition);
                break;
            case 10:
                allowClearData(viewPosition);
                break;
            case 11:
                allowUninstallApp(viewPosition);
                break;

        }

    }

    private void allowUninstallApp(final int viewPosition) {

        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(modle.allowUninstall(viewPosition));
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
                    modle.updateToUninstall(viewPosition,true);
                    view.updateMsg("该应用允许被用户卸载");
                }
            }
        });
    }

    private void allowClearData(final int viewPosition) {

        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(modle.allowClearData(viewPosition));
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
                    modle.updateToClearData(viewPosition,true);
                    view.updateMsg("该应用允许被用户清除数据");
                }
            }
        });
    }

    private void allowRunningApp(final int viewPosition) {

        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(modle.allowRunApp(viewPosition));
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
                    modle.updateToRunning(viewPosition,true);
                    view.updateMsg("该应用允许被用户运行");
                }
            }
        });

    }

    private void forceUnIntall(final int viewPosition) {

        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(modle.forceUnInstall(viewPosition));
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
                    modle.updateToUninstall(viewPosition,false);
                    view.updateMsg("该应用不允许被用户卸载");
                }
            }
        });
    }

    private void forceClearData(final int viewPosition) {

        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(modle.forceClearData(viewPosition));
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
                    modle.updateToClearData(viewPosition,false);
                    view.updateMsg("该应用不允许被用户清理数据");
                }else{

                }
            }
        });
    }

    /**
     * 不允许该应用被启动
     * @param viewPosition
     */
    private void unruningApp(final int viewPosition) {

        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(modle.unallowRunning(viewPosition));
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
                    modle.updateToRunning(viewPosition,false);
                    view.updateMsg("该应用不允许被用户运行");
                }else{

                }
            }
        });

    }
    //进程保活
    private void daemno(final int viewPosition) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(modle.allowDaemon(viewPosition));
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
                    modle.updateToStopApp(viewPosition,false);
                    view.updateMsg("该应用不允许被用户停止");
                }else{

                }
            }
        });

    }

    /**
     * 卸载应用
     * @param viewPosition
     */
    private void uninstall(final int viewPosition) {

        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(modle.uninstallApp(viewPosition));
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
                    modle.delete(viewPosition);
                    view.removeUpdate(viewPosition);
                }else{
                    view.updateMsg("该应用不允许被用户卸载");
                }
            }
        });
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
                }else{
                    view.updateMsg("该应用不允许被用户停止");
                }
            }
        });
    }

    /**
     * 清除数据
     * @param viewPosition
     */
    private void wipeData(final int viewPosition) {

        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                subscriber.onNext(modle.wipeData(viewPosition));
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
                    modle.updateWipeStatus(viewPosition);
                    view.updateView(viewPosition);
                }else{
                    view.updateMsg("该应用不允许被用户清理数据");
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
                }else{
                    view.updateMsg("该应用不允许被用户运行");
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
