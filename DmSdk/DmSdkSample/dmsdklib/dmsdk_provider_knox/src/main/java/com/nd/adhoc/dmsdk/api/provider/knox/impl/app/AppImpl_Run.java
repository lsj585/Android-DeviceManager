package com.nd.adhoc.dmsdk.api.provider.knox.impl.app;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.app.IApp_Run;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IApp_Run.class)
public class AppImpl_Run implements IApp_Run {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean startApp(@NonNull Context context,String packageName,String clsName){

        ApplicationPolicy applicationPolicy=KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
           return false;
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
           return applicationPolicy.startApp(packageName, clsName);
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return false;
    }
}
