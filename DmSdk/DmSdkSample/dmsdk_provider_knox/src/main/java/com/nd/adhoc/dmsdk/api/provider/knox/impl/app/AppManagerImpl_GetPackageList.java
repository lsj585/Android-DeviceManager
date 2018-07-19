package com.nd.adhoc.dmsdk.api.provider.knox.impl.app;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.app.IApp_GetPackageList;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;
import java.util.Arrays;
import java.util.List;

@ApiFunctionKey(DmSdkConstants.MANAGER_APPLICATION_GETPACKAGELIST)
@ApiImpl(IApp_GetPackageList.class)
@Service(IDmSdkApi.class)
public class AppManagerImpl_GetPackageList implements IApp_GetPackageList {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public List<String> getApplicationPackageList(@NonNull Context context) throws DeviceManagerSecurityException {
        ApplicationPolicy applicationPolicy=KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
        //TODO zyb 此处最高异常待定，需要核对API
        try {
            String[] packageList=applicationPolicy.getInstalledApplicationsIDList();
            if(packageList != null){
                return Arrays.asList(packageList);
            }
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return null;
    }
}
