package com.nd.adhoc.dmsdk.api.manager.license;

import android.content.Context;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

public interface ILicenseManager_DeActive extends IDeviceManager {

    void deActive() throws DeviceManagerSecurityException;
}
