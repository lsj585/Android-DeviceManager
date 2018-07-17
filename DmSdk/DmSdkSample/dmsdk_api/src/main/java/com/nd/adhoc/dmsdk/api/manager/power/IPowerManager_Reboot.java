package com.nd.adhoc.dmsdk.api.manager.power;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

/**
 * 重启
 */
public interface IPowerManager_Reboot extends IDeviceManager {
    void exec() throws DeviceManagerSecurityException;
}
