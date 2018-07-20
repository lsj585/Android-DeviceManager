package com.nd.adhoc.dmsdk.api.provider.knox.impl.pac;

import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.pac.IPackage_Install;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.sdp.android.serviceloader.annotation.Service;
import java.io.File;

@Service(IDmSdkApi.class)
@ApiImpl(IPackage_Install.class)
public class PackageImpl_Install implements IPackage_Install {
    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean install(@NonNull Context context, @NonNull String apKFile){

        if (TextUtils.isEmpty(apKFile)) {
            return false;
        }

        File file = new File(apKFile);
        //如果目标文件不存在
        if (!file.exists()) {
            return false;
        }
        try {
            ApplicationPolicy applicationPolicy = KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
            if (applicationPolicy == null) {
                return false;
            }
            return applicationPolicy.installApplication(apKFile, false);
        } catch (SecurityException  e ) {
            e.printStackTrace();
        }
        return false;
    }
}
