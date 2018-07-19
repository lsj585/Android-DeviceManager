package com.nd.adhoc.dmsdk.api.system;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * 恢复出产
 */
public interface ISystem_RestoreProduct extends IDmSdkApi {

    void execute() throws DeviceManagerSecurityException;
}
