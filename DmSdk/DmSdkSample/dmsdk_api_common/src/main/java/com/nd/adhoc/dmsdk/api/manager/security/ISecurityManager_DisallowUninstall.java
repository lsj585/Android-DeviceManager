package com.nd.adhoc.dmsdk.api.manager.security;

import com.nd.adhoc.dmsdk.api.IDeviceManager;

import java.util.List;

/**
 * 应用卸载被阻止
 */
public interface ISecurityManager_DisallowUninstall extends IDeviceManager {
    void exec(List list);
}
