package com.nd.adhoc.dmsdk.demo.presenter.impl;

import android.content.Context;

import com.nd.adhoc.dmsdk.demo.model.impl.FileManagerModel;
import com.nd.adhoc.dmsdk.demo.presenter.base.BasePresenter;
import com.nd.adhoc.dmsdk.demo.presenter.IFileManagerPresenter;
import com.nd.adhoc.dmsdk.demo.presenter.factory.ApkFireFactory;
import com.nd.adhoc.dmsdk.demo.presenter.strategy.FileControlStrategy;
import com.nd.adhoc.dmsdk.demo.view.FileManagerView;

public class FileManagerPresenter extends BasePresenter<FileManagerView,FileManagerModel> implements IFileManagerPresenter {

    private ApkFireFactory mFactory;

    public FileManagerPresenter(Context context, FileManagerView view) {
        super(context, view);
        this.mFactory=new ApkFireFactory();
    }

    @Override
    public void getFileAppList() {
        if(this.mFactory==null){
            return;
        }
        FileControlStrategy strategy=this.mFactory.getStrategy(ApkFireFactory.STRATEGY_FILE_GETLIST);
        if(strategy != null){
            strategy.execute(0,view,modle);
        }
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
        if(this.mFactory==null){
            return;
        }
        FileControlStrategy strategy= this.mFactory.getStrategy(ApkFireFactory.STRATEGY_FILE_INSTALLAPK);
        if(strategy != null){
            strategy.execute(viewPosition,view,modle);
        }
    }

    @Override
    public void onDestroy() {
        modle=null;
        mFactory=null;
        super.onDestroy();
    }
}
