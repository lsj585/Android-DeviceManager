package com.nd.adhoc.dmsdk.api.knox.manager;

import android.app.enterprise.EnterpriseDeviceManager;
import android.content.Context;
import android.util.Log;


class BaseManager {

    protected Context context ;

    protected EnterpriseDeviceManager deviceManager;

    public Context getContext() {

        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        Log.i(this.getClass().getName(),"context:="+context);
        deviceManager=new EnterpriseDeviceManager(context);
        Log.i(this.getClass().getName(),"BaseManager:="+deviceManager);
    }
}
