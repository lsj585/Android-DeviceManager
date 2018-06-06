package com.nd.adhoc.dmsdk.api.manager.system;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

public interface ISystemManager_Reboot extends IDeviceManager {
    /**
     * 重启
     */
    void execute() throws DeviceManagerSecurityException;
}
