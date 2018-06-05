package com.nd.adhoc.dmsdk.api.provider.aosp.hardware;
import android.app.admin.DevicePolicyManager;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.UserManager;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.hardware.IBluetoothManager;

public class BluetoothManagerImpl implements IBluetoothManager {

    @Override
    public void open(@NonNull Context context) throws DeviceManagerSecurityException {
        turnOff(context,true);
        enableBluetooth();
    }

    @Override
    public void close(@NonNull Context context) throws DeviceManagerSecurityException {
        turnOff(context,false);
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
                }else{
                    throw new DeviceManagerSecurityException(ErrorCode.DEFAULT_ERROR_CODE);
                }
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

        DeviceManagerContainer container = DeviceManagerContainer.getInstance();

        DevicePolicyManager devicePolicyManager = container.getDevicePolicyManager();

        ComponentName componentName = container.getComponentName();

        if (container.getComponentName() == null) {
            return;
        }

        if (devicePolicyManager == null) {
            return;
        }

        if(isOpen){
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    devicePolicyManager.addUserRestriction(componentName, UserManager.DISALLOW_BLUETOOTH);
                }else{
                    throw  new DeviceManagerSecurityException(ErrorCode.DEFAULT_ERROR_CODE);
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }else{
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    devicePolicyManager.clearUserRestriction(componentName, UserManager.DISALLOW_BLUETOOTH);
                }else{
                    throw  new DeviceManagerSecurityException(ErrorCode.DEFAULT_ERROR_CODE);
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

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
