package com.nd.adhoc.dmsdk.api.system;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

public interface ISystem_Reboot extends IDmSdkApi {
    /**
     * 重启
     */
    void execReboot() throws DeviceManagerSecurityException;
}
