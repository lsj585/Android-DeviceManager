package com.nd.adhoc.dmsdk.demo.presenter.impl;

import android.content.Context;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.demo.model.BaseModel;
import com.nd.adhoc.dmsdk.demo.presenter.BasePresenter;
import com.nd.adhoc.dmsdk.demo.view.BaseView;

/**
 * 执行相关业务
 */
public class ActiveLicensePresenter extends BasePresenter<BaseView,BaseModel> implements IActivePresenter {

    private DeviceManagerSdk deviceManagerSdk;

    public ActiveLicensePresenter(Context context, BaseView view) {
        super(context, view);
    }


    @Override
    public void activeLicense() {
        DeviceManagerSdk.getInstance().registerLicense(context);
    }

    @Override
    public void onDestroy() {
        DeviceManagerSdk.getInstance().release();
        super.onDestroy();
    }
}
