package com.nd.adhoc.dmsdk.api.provider.aosp.app;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_GetPackageList;

import java.util.ArrayList;
import java.util.List;

public class ApplicationManagerImpl_GetPackageList implements IApplicationManager_GetPackageList{


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
