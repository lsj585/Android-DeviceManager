package com.nd.adhoc.dmsdk.api.manager.license;

import android.content.Context;

import com.nd.adhoc.dmsdk.api.IDeviceManager;

public interface LicenseManager_Active extends IDeviceManager {

    void active(Context context);
}
