package com.nd.adhoc.dmsdk.api.provider.knox.impl.security;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.security.ISecurity_DisallowWipeData;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.List;
//TODO 记得改
@Service(IDmSdkApi.class)
@ApiImpl(ISecurity_DisallowWipeData.class)
public class SecurityImpl_DisallowWipeData implements ISecurity_DisallowWipeData {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean disallowClearData(@NonNull Context context, @NonNull List<String> packages) {
        ApplicationPolicy applicationPolicy= KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
            return false;
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            return applicationPolicy.addPackagesToClearDataWhiteList(packages);
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean disallowClearCacheData(@NonNull Context context, @NonNull List<String> packages) {
        ApplicationPolicy applicationPolicy= KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
            return false;
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            return applicationPolicy.addPackagesToClearDataWhiteList(packages);
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return false;
    }
}
