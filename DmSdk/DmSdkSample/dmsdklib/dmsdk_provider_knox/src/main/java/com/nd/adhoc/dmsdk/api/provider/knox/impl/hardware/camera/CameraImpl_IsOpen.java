package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.camera;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.camera.ICamera_IsOpen;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(ICamera_IsOpen.class)
public class CameraImpl_IsOpen implements ICamera_IsOpen {
    @Override
    public boolean isOpen(@NonNull Context context) {
        RestrictionPolicy restrictionPolicy= null;
        try {
            restrictionPolicy = Verification.isRestrictionPolicyNull(context);
            return restrictionPolicy.isCameraEnabled(true);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
