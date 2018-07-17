package com.nd.adhoc.dmsdk.api.provider.knox.app;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_Run;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IApplicationManager_Run.class)
public class ApplicationManagerImpl_Run implements IApplicationManager_Run {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean startApp(@NonNull Context context,String packageName,String clsName) throws DeviceManagerSecurityException {

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
