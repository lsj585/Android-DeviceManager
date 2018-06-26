package com.nd.adhoc.dmsdk.api.provider.knox.security;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowRun;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import java.util.List;

public class SecurityManagerImpl_DisallowRun implements ISecurityManager_DisallowRun{
    @Override
    public void release(@NonNull Context context) {

    }
    @Override
    public boolean addPackageToRunList(@NonNull Context context, @NonNull List list){
        ApplicationPolicy applicationPolicy=KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
            return false;
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            List<String> blacks= applicationPolicy.addPackagesToPreventStartBlackList(list);
            if(blacks==null){
                return false;
            }
            return true;
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return false;
    }
}
