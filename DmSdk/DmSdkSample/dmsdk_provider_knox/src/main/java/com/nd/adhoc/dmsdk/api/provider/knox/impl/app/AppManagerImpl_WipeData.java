package com.nd.adhoc.dmsdk.api.provider.knox.impl.app;

import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.app.IApp_WipeData;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

@ApiFunctionKey(DmSdkConstants.MANAGER_APPLICATION_WIPEDATA)
@Service(IDmSdkApi.class)
@ApiImpl(IApp_WipeData.class)
public class AppManagerImpl_WipeData implements IApp_WipeData {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean clearData(@NonNull Context context, String packageName) throws DeviceManagerSecurityException {

        ApplicationPolicy applicationPolicy = KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if (applicationPolicy == null) {
            return false;
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            return applicationPolicy.wipeApplicationData(packageName);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }
}
