package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.mobiledata;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.mobiledata.IMobileData_IsOpen;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IMobileData_IsOpen.class)
public class MobileDataImpl_IsOpen implements IMobileData_IsOpen {

    @Override
    public boolean isOpen(@NonNull Context context){
        RestrictionPolicy restrictionPolicy= null;
        try {
            restrictionPolicy = Verification.isRestrictionPolicyNull(context);
            return restrictionPolicy.isMicrophoneEnabled(true);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void release(@NonNull Context context) {

    }

}
