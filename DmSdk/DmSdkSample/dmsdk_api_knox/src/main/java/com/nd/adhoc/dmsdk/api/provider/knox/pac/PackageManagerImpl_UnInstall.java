package com.nd.adhoc.dmsdk.api.provider.knox.pac;
import android.app.enterprise.ApplicationPolicy;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.pac.IPackageManager_Uninstall;
import com.nd.adhoc.dmsdk.api.provider.knox.KnoxDeviceManagerFactory;

public class PackageManagerImpl_UnInstall  implements IPackageManager_Uninstall {

    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public void uninstall(@NonNull Context context, @NonNull String packageName) throws DeviceManagerSecurityException {
        try {
            if (TextUtils.isEmpty(packageName)) {
                throw new NullPointerException("");
            }
            ApplicationPolicy applicationPolicy = KnoxDeviceManagerFactory.getInstance().getApplicationPolicy(context);
            if (applicationPolicy == null) {
                throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
            }
            try{
               boolean isSuccess= applicationPolicy.uninstallApplication(packageName,false);
               if(!isSuccess){
                   throw new DeviceManagerSecurityException(ErrorCode.DEFAULT_OPERATION_ERROR);
               }
            }catch (SecurityException e){
                throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            //TODO zyb 此处最高异常待定，需要核对API
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }
}
