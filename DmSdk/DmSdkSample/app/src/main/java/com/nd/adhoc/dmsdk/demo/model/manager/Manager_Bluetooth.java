package com.nd.adhoc.dmsdk.demo.model.manager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.api.hardware.bluetooth.IBluetooth_Close;
import com.nd.adhoc.dmsdk.api.hardware.bluetooth.IBluetooth_IsOpen;
import com.nd.adhoc.dmsdk.api.hardware.bluetooth.IBluetooth_Open;
import com.nd.adhoc.dmsdk.demo.model.manager.base.IDeviceManager;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;


/**
 * @author richsjeson
 */

public class Manager_Bluetooth implements IDeviceManager {


    public boolean execute(@NonNull Context context) {

        if (!isOpen(context)) {
            return openBluetooth(context);
        } else {
            return closeBluetooth(context);
        }
    }


    public boolean isOpen(@NonNull Context context) {

        IBluetooth_IsOpen bluetoothIsOpen;
        try {
            bluetoothIsOpen = (IBluetooth_IsOpen) DeviceManagerSdk.getInstance().getApi(IBluetooth_IsOpen.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return bluetoothIsOpen.isOpen(context);
    }

    private boolean openBluetooth(@NonNull Context context) {
        IBluetooth_Open bluetoothOpen;
        try {
            bluetoothOpen = (IBluetooth_Open) DeviceManagerSdk.getInstance().getApi(IBluetooth_Open.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return bluetoothOpen.open(context);
    }


    private boolean closeBluetooth(@NonNull Context context) {
        IBluetooth_Close bluetoothClose;
        try {
            bluetoothClose = (IBluetooth_Close) DeviceManagerSdk.getInstance().getApi(IBluetooth_Close.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return bluetoothClose.close(context);
    }

}
