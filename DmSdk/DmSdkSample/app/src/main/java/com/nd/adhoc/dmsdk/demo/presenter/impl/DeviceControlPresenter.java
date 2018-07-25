package com.nd.adhoc.dmsdk.demo.presenter.impl;

import android.content.Context;
import com.nd.adhoc.dmsdk.demo.model.impl.DeviceControlModel;
import com.nd.adhoc.dmsdk.demo.presenter.BasePresenter;
import com.nd.adhoc.dmsdk.demo.presenter.IDeviceControlPresenter;
import com.nd.adhoc.dmsdk.demo.strategy.DeviceManagerFactory;
import com.nd.adhoc.dmsdk.demo.strategy.DeviceStrategy;
import com.nd.adhoc.dmsdk.demo.view.DeviceControlView;

import static com.nd.adhoc.dmsdk.demo.strategy.DeviceManagerFactory.STRATEGY_DEVICE_GETLIST;
import static com.nd.adhoc.dmsdk.demo.strategy.DeviceManagerFactory.STRATEGY_DEVICE_OPERATION;

public class DeviceControlPresenter extends BasePresenter<DeviceControlView,DeviceControlModel> implements IDeviceControlPresenter {

    private DeviceManagerFactory mFactory;

    public DeviceControlPresenter(Context context, DeviceControlView view) {
        super(context, view);
        mFactory=new DeviceManagerFactory();
    }

    /**
     * 获取参数列表
     */
    public void getDeviceControlList(){
        verifyNull();
        DeviceStrategy deviceStrategy= mFactory.getStrategy(STRATEGY_DEVICE_GETLIST);
        deviceStrategy.execute(0,view,modle);
    }

    @Override
    public void onClick(final int position) {
        verifyNull();
        DeviceStrategy deviceStrategy= mFactory.getStrategy(STRATEGY_DEVICE_OPERATION);
        deviceStrategy.execute(position,view,modle);
    }

    @Override
    public void onDestroy() {
        modle=null;
        mFactory=null;
        super.onDestroy();
    }

    private void verifyNull(){
        if(modle==null){
            return;
        }
        //此处的strategy需要通过注解注入初始化
        if(mFactory==null){
            mFactory=new DeviceManagerFactory();
        }
    }
}
