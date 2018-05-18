package com.nd.adhoc.dmsdk.demo.presenter.impl;

import android.content.Context;

import com.nd.adhoc.dmsdk.api.knox.manager.DeviceSdkApiManager;
import com.nd.adhoc.dmsdk.demo.model.BaseModel;
import com.nd.adhoc.dmsdk.demo.presenter.BasePresenter;
import com.nd.adhoc.dmsdk.demo.view.BaseView;

/**
 * 执行相关业务
 */
public class ActiveLicensePresenter extends BasePresenter<BaseView,BaseModel> implements IActivePresenter {

    private DeviceSdkApiManager manager;

    public ActiveLicensePresenter(Context context, BaseView view) {
        super(context, view);
        manager=new DeviceSdkApiManager(context);
    }


    @Override
    public void activeLicense() {
        manager.activeLicense();
    }

    @Override
    public void onDestroy() {
        manager.release();
        super.onDestroy();
    }
}
