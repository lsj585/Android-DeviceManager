package com.nd.adhoc.dmsdk.api.manager.system;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

import java.util.List;

/**
 * 应用管理
 */
public interface ISystemManager_ApplicationList extends IDeviceManager {


    List getApplicationPakcageList(@NonNull Context context) throws DeviceManagerSecurityException;
}
