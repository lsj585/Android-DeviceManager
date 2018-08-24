package com.nd.adhoc.dmsdk.demo.model.manager;
import android.content.Context;
import android.support.annotation.NonNull;
import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.api.hardware.devicelock.IDeviceLock_IsLock;
import com.nd.adhoc.dmsdk.api.hardware.devicelock.IDeviceLock_Lock;
import com.nd.adhoc.dmsdk.api.hardware.devicelock.IDeviceLock_Unlock;
import com.nd.adhoc.dmsdk.demo.model.manager.base.IDeviceManager;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;


/**
 * @author richsjeson
 */

public class Manager_DeviceLock implements IDeviceManager {


    public boolean execute(@NonNull Context context) {

        if (!isOpen(context)) {
            return lock(context);
        } else {
            return unlock(context);
        }
    }


    public boolean isOpen(@NonNull Context context) {

        IDeviceLock_IsLock deviceIsLock;
        try {
            deviceIsLock = (IDeviceLock_IsLock) DeviceManagerSdk.getInstance().getApi(IDeviceLock_IsLock.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return deviceIsLock.isOpen(context);
    }

    private boolean lock(@NonNull Context context) {
        IDeviceLock_Lock deviceLock;
        try {
            deviceLock = (IDeviceLock_Lock) DeviceManagerSdk.getInstance().getApi(IDeviceLock_Lock.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return deviceLock.open(context);
    }


    private boolean unlock(@NonNull Context context) {
        IDeviceLock_Unlock deviceUnlock;
        try {
            deviceUnlock = (IDeviceLock_Unlock) DeviceManagerSdk.getInstance().getApi(IDeviceLock_Unlock.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        return deviceUnlock.close(context);
    }

}
