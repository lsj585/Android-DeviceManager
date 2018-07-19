package com.nd.adhoc.dmsdk.api.license;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;

public interface ILicense_Active extends IDmSdkApi {

    void active(@NonNull Context context) throws DeviceManagerSecurityException;
}
