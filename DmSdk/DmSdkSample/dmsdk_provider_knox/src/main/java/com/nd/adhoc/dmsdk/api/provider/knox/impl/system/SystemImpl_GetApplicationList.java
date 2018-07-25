package com.nd.adhoc.dmsdk.api.provider.knox.impl.system;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.system.ISystem_ApplicationList;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.Arrays;
import java.util.List;


@Service(IDmSdkApi.class)
@ApiImpl(ISystem_ApplicationList.class)
public class SystemImpl_GetApplicationList implements ISystem_ApplicationList {

    @Override
    public List<String> getApplicationPakcageList(@NonNull Context context) {
        try {
            ApplicationPolicy applicationPolicy = KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
            if(applicationPolicy==null){
                return null;
            }

            String [] appPackages=applicationPolicy.getInstalledApplicationsIDList();

            if(appPackages==null){
                return null;
            }
            return Arrays.asList(appPackages);
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
