package com.nd.adhoc.dmsdk.api.provider.knox.impl.hardware.bluetooth;

import android.app.enterprise.RestrictionPolicy;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.bluetooth.IBluetooth_IsOpen;
import com.nd.adhoc.dmsdk.api.provider.knox.utils.Verification;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;
@ApiFunctionKey(DmSdkConstants.MANAGER_HARDWARE_BLUETOOTH)
@Service(IDmSdkApi.class)
@ApiImpl(IBluetooth_IsOpen.class)
public class BluetoothImpl_IsOpen implements IBluetooth_IsOpen {

    @Override
    public boolean isOpen(@NonNull Context context) throws DeviceManagerSecurityException {
        RestrictionPolicy restrictionPolicy= Verification.isRestrictionPolicyNull(context);
        return restrictionPolicy.isBluetoothEnabled(true);
    }
    @Override
    public void release(@NonNull Context context) {

    }
}
