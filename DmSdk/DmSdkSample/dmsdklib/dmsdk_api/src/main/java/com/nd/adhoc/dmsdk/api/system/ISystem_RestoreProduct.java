package com.nd.adhoc.dmsdk.api.system;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

/**
 * @author richsjeson
 * 恢复出产
 */
public interface ISystem_RestoreProduct extends IDmSdkApi {
    /**
     * 恢复出产设置
     * @throws DeviceManagerSecurityException
     */
    void execRestoreProduct();
}
