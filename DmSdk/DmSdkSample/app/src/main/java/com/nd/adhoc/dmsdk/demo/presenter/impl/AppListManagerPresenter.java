package com.nd.adhoc.dmsdk.demo.presenter.impl;

import android.content.Context;

import com.nd.adhoc.dmsdk.demo.model.impl.AppListManagerModel;
import com.nd.adhoc.dmsdk.demo.presenter.base.BasePresenter;
import com.nd.adhoc.dmsdk.demo.presenter.IAppManagerPresenter;
import com.nd.adhoc.dmsdk.demo.presenter.factory.AppManagerFactory;
import com.nd.adhoc.dmsdk.demo.presenter.strategy.AppStrategy;
import com.nd.adhoc.dmsdk.demo.view.AppManagerView;

public class AppListManagerPresenter extends BasePresenter<AppManagerView,AppListManagerModel> implements IAppManagerPresenter {


    private AppManagerFactory mFactory;

    public AppListManagerPresenter(Context context, AppManagerView view) {
        super(context, view);
        mFactory=new AppManagerFactory();
    }
    @Override
    public void getApplist() {
        verifyNull();
        AppStrategy applicationStrategy =mFactory.getStrategy(AppManagerFactory.STRATEGY_GET_APPLIST);
        if(applicationStrategy == null) {
            return;
        }
        applicationStrategy.execute(0, view, modle);
    }

    @Override
    public void onClick(int dialogPos,int viewPosition) {
        verifyNull();
        AppStrategy applicationStrategy =mFactory.getStrategy(dialogPos);
        if(applicationStrategy== null) {
            return;
        }
        applicationStrategy.execute(viewPosition, view, modle);
    }


    private void verifyNull(){
        if(modle==null){
            return;
        }
        //此处的strategy需要通过注解注入初始化
        if(mFactory==null){
            mFactory=new AppManagerFactory();
        }
    }

    @Override
    public void onDestroy() {
        modle=null;
        mFactory=null;
        super.onDestroy();
    }
}
