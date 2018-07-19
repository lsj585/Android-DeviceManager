package com.nd.adhoc.dmsdk.api.provider.knox.impl.pac;

import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.pac.IPackage_Install;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

import java.io.File;
import java.io.FileNotFoundException;
@Service(IDmSdkApi.class)
@ApiImpl(IPackage_Install.class)
@ApiFunctionKey(DmSdkConstants.MANAGER_PACKAGE_INSTALL)
public class PackageImpl_Install implements IPackage_Install {
    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public boolean install(@NonNull Context context, @NonNull String apKFile) throws DeviceManagerSecurityException, FileNotFoundException {

        if (TextUtils.isEmpty(apKFile)) {
            throw new NullPointerException("");
        }

        File file = new File(apKFile);
        //如果目标文件不存在
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        try {
            ApplicationPolicy applicationPolicy = KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
            if (applicationPolicy == null) {
                return false;
            }
            return applicationPolicy.installApplication(apKFile, false);
        } catch (SecurityException  e ) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);

    }
}
