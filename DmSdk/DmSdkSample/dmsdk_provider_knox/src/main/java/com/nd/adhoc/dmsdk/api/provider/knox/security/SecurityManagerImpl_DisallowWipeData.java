package com.nd.adhoc.dmsdk.api.provider.knox.security;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowWipeData;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.List;
@Service(ISecurityManager_DisallowWipeData.class)
public class SecurityManagerImpl_DisallowWipeData implements ISecurityManager_DisallowWipeData {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean addPackageToClearDataList(@NonNull Context context, @NonNull List packages) {
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
    public boolean addPackageToClearCacheList(@NonNull Context context, @NonNull List packages){
        ApplicationPolicy applicationPolicy= KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
            return false;
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            return applicationPolicy.addPackagesToClearCacheWhiteList(packages);
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return false;
    }
}
