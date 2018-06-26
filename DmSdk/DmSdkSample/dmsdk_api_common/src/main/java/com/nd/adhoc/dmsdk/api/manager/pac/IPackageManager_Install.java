package com.nd.adhoc.dmsdk.api.manager.pac;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;

import java.io.FileNotFoundException;

/**
 * 安装包管理---静默安装和卸载
 */
public interface IPackageManager_Install extends IDeviceManager {
    /**
     * 安装应用
     *
     * @param apKFile 路径
     */
    boolean install(@NonNull Context context, @NonNull String apKFile) throws DeviceManagerSecurityException, FileNotFoundException;
}
