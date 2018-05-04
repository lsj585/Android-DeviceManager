package com.nd.adhoc.dmsdk.demo.loader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import com.nd.adhoc.dmsdk.demo.common.DeviceFunction;
import com.nd.adhoc.dmsdk.api.knox.manager.DeviceApiManager;

public class ActiveLoader extends AsyncTaskLoader {

    private int functionNumber;

    private DeviceApiManager manager;

    public ActiveLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public Object loadInBackground() {
        switch (functionNumber){
            case DeviceFunction.ACTIVE_LIENCE:
                manager=new DeviceApiManager(getContext());
                manager.activeLicense();
                break;
        }
        return null;
    }

    public void request(int functionNumber){

        this.functionNumber=functionNumber;
        forceLoad();
    }

}
