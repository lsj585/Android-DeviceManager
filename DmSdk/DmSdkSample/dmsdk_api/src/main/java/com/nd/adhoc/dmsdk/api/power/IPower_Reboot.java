package com.nd.adhoc.dmsdk.api.power;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * 重启
 */
public interface IPower_Reboot extends IDmSdkApi {
    void exec() throws DeviceManagerSecurityException;
}
