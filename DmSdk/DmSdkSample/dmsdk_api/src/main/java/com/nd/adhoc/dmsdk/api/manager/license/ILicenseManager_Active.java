package com.nd.adhoc.dmsdk.api.manager.license;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

public interface ILicenseManager_Active extends IDeviceManager {

    void active(@NonNull Context context) throws DeviceManagerSecurityException;
}
