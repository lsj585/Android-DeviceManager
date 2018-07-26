package com.nd.adhoc.dmsdk.api.provider.aosp.app;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.app.IApp_GetPackageList;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.util.ArrayList;
import java.util.List;
@Service(IDmSdkApi.class)
@ApiImpl(IApp_GetPackageList.class)
public class AppImpl_GetPackageList implements IApp_GetPackageList {


    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public List<String> getApplicationPackageList(@NonNull Context context){
        List<String> packages=new ArrayList<String>();
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageInfoList = packageManager.getInstalledPackages(0);
        for (int i=0;i<packageInfoList.size();i++){
            PackageInfo info =packageInfoList.get(i);
            if(info != null && !TextUtils.isEmpty(info.packageName)) {
                packages.add(info.packageName);
            }
        }
        return packages;
    }
}
