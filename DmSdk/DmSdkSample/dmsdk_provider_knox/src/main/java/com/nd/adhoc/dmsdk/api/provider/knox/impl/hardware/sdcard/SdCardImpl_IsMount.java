package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.sdcard;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.sdcard.ISdCard_IsMount;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(ISdCard_IsMount.class)
public class SdCardImpl_IsMount implements ISdCard_IsMount {
    @Override
    public boolean isOpen(@NonNull Context context) {
        RestrictionPolicy restrictionPolicy= null;
        try {
            restrictionPolicy = Verification.isRestrictionPolicyNull(context);
            return restrictionPolicy.isSdCardEnabled();
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void release(@NonNull Context context) {

    }

}
