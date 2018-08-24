package com.nd.adhoc.dmsdk.api.provider.knox.impl.app;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.app.IApp_GetPackageList;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;
import java.util.Arrays;
import java.util.List;
@ApiImpl(IApp_GetPackageList.class)
@Service(IDmSdkApi.class)
public class AppImpl_GetPackageList implements IApp_GetPackageList {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public List<String> getApplicationPackageList(@NonNull Context context) {
        ApplicationPolicy applicationPolicy=KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
        if(applicationPolicy==null){
            return null;
        }
        try {
            String[] packageList=applicationPolicy.getInstalledApplicationsIDList();
            if(packageList == null){
                return null;
            }
            return Arrays.asList(packageList);
        }catch (SecurityException e){
            e.printStackTrace();
        }
        return null;
    }
}
