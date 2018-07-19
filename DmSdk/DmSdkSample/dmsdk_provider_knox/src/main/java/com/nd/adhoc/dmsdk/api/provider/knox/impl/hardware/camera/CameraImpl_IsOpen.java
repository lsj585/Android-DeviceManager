package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.camera;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.camera.ICamera_IsOpen;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

@ApiFunctionKey(DmSdkConstants.MANAGER_HARDWARE_CAMERA)
@Service(IDmSdkApi.class)
@ApiImpl(ICamera_IsOpen.class)
public class CameraImpl_IsOpen implements ICamera_IsOpen {
    @Override
    public boolean isOpen(@NonNull Context context) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        try {
            return restrictionPolicy.isCameraEnabled(true);
        }catch (SecurityException e){
            //TODO 此处要加入异常提示--Error Code中枚举值要更新
            throw  new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
