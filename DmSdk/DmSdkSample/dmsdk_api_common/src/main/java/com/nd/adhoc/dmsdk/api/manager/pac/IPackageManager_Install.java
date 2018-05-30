package com.nd.adhoc.dmsdk.api.manager.pac;

import com.nd.adhoc.dmsdk.api.IDeviceManager;

/**
 * 安装包管理---静默安装和卸载
 */
public interface IPackageManager_Install extends IDeviceManager {
    /**
     * 安装应用
     *
     * @param apKFile 路径
     */
    void exec(String apKFile);
}
