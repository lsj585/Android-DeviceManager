package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.usb;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.usb.IUsb_IsConnect;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IUsb_IsConnect.class)
public class UsbImpl_IsConnect implements IUsb_IsConnect {
    @Override
    public boolean isOpen(@NonNull Context context){
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        try {
            return restrictionPolicy.isUsbHostStorageAllowed();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void release(@NonNull Context context) {

    }
}
