package com.nd.adhoc.dmsdk.api.manager.system;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

/**
 * 恢复出产
 */
public interface ISystemManager_RestoreProduct extends IDeviceManager {

    void execute() throws DeviceManagerSecurityException;
}
