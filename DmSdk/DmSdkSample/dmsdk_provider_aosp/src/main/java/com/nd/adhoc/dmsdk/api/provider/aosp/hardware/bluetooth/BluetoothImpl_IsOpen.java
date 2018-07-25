package com.nd.adhoc.dmsdk.api.provider.aosp.hardware.bluetooth;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.UserManager;
import android.support.annotation.NonNull;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiFunctionKey;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.bluetooth.IBluetooth_IsOpen;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IBluetooth_IsOpen.class)
public class BluetoothImpl_IsOpen implements IBluetooth_IsOpen {

    @Override
    public boolean isOpen(@NonNull Context context){
        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        if (container.getComponentName() == null) {
            return false;
        }

        if (devicePolicyManager == null) {
            return false;
        }
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Bundle bundle= devicePolicyManager.getUserRestrictions(componentName);
                if(bundle != null) {
                    return bundle.getBoolean(UserManager.DISALLOW_BLUETOOTH);
                }}
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void release(@NonNull Context context) {

    }
}
