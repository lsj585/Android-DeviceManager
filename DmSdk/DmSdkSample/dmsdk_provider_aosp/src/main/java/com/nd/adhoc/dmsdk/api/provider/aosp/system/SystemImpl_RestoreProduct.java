package com.nd.adhoc.dmsdk.api.provider.aosp.system;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.system.ISystem_RestoreProduct;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;
import com.nd.sdp.android.serviceloader.annotation.Service;

import static android.app.admin.DevicePolicyManager.WIPE_RESET_PROTECTION_DATA;
@Service(ISystem_RestoreProduct.class)
public class SystemImpl_RestoreProduct implements ISystem_RestoreProduct {



    @Override
    public void release(@NonNull Context context) {

    }

    @Override
    public void execute() throws DeviceManagerSecurityException {
        DeviceManagerContainer container = DeviceManagerContainer.getInstance();
        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();
        if (devicePolicyManager == null) {
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
        try {
            devicePolicyManager.wipeData(WIPE_RESET_PROTECTION_DATA);
        }catch (SecurityException e){
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }
}
