package com.nd.adhoc.dmsdk.api.manager.security;

import com.nd.adhoc.dmsdk.api.IDeviceManager;

import java.util.List;

/**
 * 应用卸载被阻止
 */
public interface SecurityManager_Disallow_Uninstall extends IDeviceManager {
    void exec(List list);
}
