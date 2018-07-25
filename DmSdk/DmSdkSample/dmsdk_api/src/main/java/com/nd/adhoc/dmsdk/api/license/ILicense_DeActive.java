package com.nd.adhoc.dmsdk.api.license;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

public interface ILicense_DeActive extends IDmSdkApi {

    void deActive() throws DeviceManagerSecurityException;
}
