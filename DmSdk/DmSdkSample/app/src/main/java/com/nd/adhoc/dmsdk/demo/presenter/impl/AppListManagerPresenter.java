package com.nd.adhoc.dmsdk.demo.presenter.impl;

import android.content.Context;

import com.nd.adhoc.dmsdk.demo.model.impl.AppListManagerModel;
import com.nd.adhoc.dmsdk.demo.presenter.BasePresenter;
import com.nd.adhoc.dmsdk.demo.presenter.IAppManagerPresenter;
import com.nd.adhoc.dmsdk.demo.strategy.ApplicationManagerFactory;
import com.nd.adhoc.dmsdk.demo.strategy.ApplicationStrategy;
import com.nd.adhoc.dmsdk.demo.view.AppManagerView;

public class AppListManagerPresenter extends BasePresenter<AppManagerView,AppListManagerModel> implements IAppManagerPresenter {


    private ApplicationManagerFactory mFactory;

    public AppListManagerPresenter(Context context, AppManagerView view) {
        super(context, view);
        mFactory=new ApplicationManagerFactory();
    }
    @Override
    public void getApplist() {
        verifyNull();
        ApplicationStrategy applicationStrategy =mFactory.getStrategy(ApplicationManagerFactory.STRATEGY_GET_APPLIST);
        applicationStrategy.execute(0,view,modle);
    }

    @Override
    public void onClick(int dialogPos,int viewPosition) {
        verifyNull();
        ApplicationStrategy applicationStrategy =mFactory.getStrategy(dialogPos);
        applicationStrategy.execute(viewPosition,view,modle);
    }


    private void verifyNull(){
        if(modle==null){
            return;
        }
        //此处的strategy需要通过注解注入初始化
        if(mFactory==null){
            mFactory=new ApplicationManagerFactory();
        }
    }

    @Override
    public void onDestroy() {
        modle=null;
        mFactory=null;
        super.onDestroy();
    }
}
