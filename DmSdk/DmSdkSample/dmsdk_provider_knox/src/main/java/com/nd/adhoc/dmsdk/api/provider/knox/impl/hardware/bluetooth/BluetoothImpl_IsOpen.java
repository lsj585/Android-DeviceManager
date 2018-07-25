package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.bluetooth;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.bluetooth.IBluetooth_IsOpen;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IBluetooth_IsOpen.class)
public class BluetoothImpl_IsOpen implements IBluetooth_IsOpen {

    @Override
    public boolean isOpen(@NonNull Context context){
        RestrictionPolicy restrictionPolicy;
        try {
            restrictionPolicy = Verification.isRestrictionPolicyNull(context);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        return restrictionPolicy.isBluetoothEnabled(true);
    }
    @Override
    public void release(@NonNull Context context) {

    }
}
