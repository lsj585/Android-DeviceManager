package com.nd.adhoc.dmsdk.api.provider.knox.impl.security;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.security.ISecurity_AllowStop;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.List;


@Service(IDmSdkApi.class)
@ApiImpl(ISecurity_AllowStop.class)
public class SecurityImpl_AllowStop implements ISecurity_AllowStop {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean allowStop(@NonNull Context context, @NonNull List<String> pakcages) {
        ApplicationPolicy applicationPolicy=KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
            return false;
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            applicationPolicy.removePackagesFromForceStopBlackList(pakcages);
            return true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return false;
    }
}
