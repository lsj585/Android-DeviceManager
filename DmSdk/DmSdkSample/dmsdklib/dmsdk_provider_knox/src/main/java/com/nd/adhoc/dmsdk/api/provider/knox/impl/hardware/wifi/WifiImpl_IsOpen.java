package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.wifi;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.wifi.IWifi_IsOpen;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IWifi_IsOpen.class)
public class WifiImpl_IsOpen implements IWifi_IsOpen {

    @Override
    public boolean isOpen(@NonNull Context context){
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        try {
            return restrictionPolicy.isWiFiEnabled(true);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void release(@NonNull Context context) {

    }
}
