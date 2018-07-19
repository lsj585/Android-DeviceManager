package com.nd.adhoc.dmsdk.api.provider.aosp.hardware;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.UserManager;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.hardware.bluetooth.IBluetooth_Open;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.provider.aosp.utils.DeviceControlUtils;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IBluetooth_Open.class)
public class BluetoothManagerImpl implements IBluetooth_Open {

    @Override
    public boolean open(@NonNull Context context) {
        try {
            turnOff(context,true);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        enableBluetooth();
        return true;

    }

    @Override
    public boolean close(@NonNull Context context) {
        try {
            turnOff(context,false);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean isOpen(@NonNull Context context) throws DeviceManagerSecurityException {
        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        if (container.getComponentName() == null) {
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }

        if (devicePolicyManager == null) {
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                Bundle bundle= devicePolicyManager.getUserRestrictions(componentName);
                if(bundle != null) {
                    return bundle.getBoolean(UserManager.DISALLOW_BLUETOOTH);
                }
                throw new DeviceManagerSecurityException(ErrorCode.DEFAULT_ERROR_CODE);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new DeviceManagerSecurityException(ErrorCode.ERROR_CODE_CONSTRUCT_NO_INSTANCE);
        }
        return false;
    }

    @Override
    public void release(@NonNull Context context) {

    }


    private void turnOff(@NonNull Context context,boolean isOpen) throws DeviceManagerSecurityException {
        DeviceControlUtils.operation(context,isOpen,UserManager.DISALLOW_BLUETOOTH);
    }


    /**
     * 开启蓝牙开关
     */
    private void enableBluetooth() {

        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null)
        {
            bluetoothAdapter.enable();
        }
    }
}
