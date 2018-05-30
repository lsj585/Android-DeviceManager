package com.nd.adhoc.dmsdk.api.manager.screen;

import com.nd.adhoc.dmsdk.api.IDeviceManager;

/**
 * 系统屏保密码设置
 */
public interface ScreenManager_Passwd extends IDeviceManager {
    /**
     * 设置屏保的密码
     * @param passwd 密码
     */
    void exec(String passwd);
}
