package com.nd.adhoc.dmsdk.api.manager.pac;

import com.nd.adhoc.dmsdk.api.IDeviceManager;

/**
 * 安装包管理---静默安装和卸载
 */
public interface PackageManager_Uninstall extends IDeviceManager {
    /**
     * 卸载应用
     * @param packageName
     */
    void exec(String packageName);
}
