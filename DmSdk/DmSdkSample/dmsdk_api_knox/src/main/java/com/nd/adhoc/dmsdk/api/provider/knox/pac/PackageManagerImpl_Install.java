package com.nd.adhoc.dmsdk.api.provider.knox.pac;

import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.pac.IPackageManager_Install;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;

import java.io.File;
import java.io.FileNotFoundException;

public class PackageManagerImpl_Install implements IPackageManager_Install {
    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public void install(@NonNull Context context, @NonNull String apKFile) throws DeviceManagerSecurityException, FileNotFoundException {

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
                throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
            }
            boolean isSuccess = applicationPolicy.installApplication(apKFile, false);
            if (!isSuccess) {
                throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        } catch (RuntimeException e) {
            e.printStackTrace();
            //TODO zyb 此处最高异常待定，需要核对API
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }
}
