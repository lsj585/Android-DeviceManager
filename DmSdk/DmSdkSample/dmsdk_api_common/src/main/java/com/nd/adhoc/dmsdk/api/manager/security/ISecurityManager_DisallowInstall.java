package com.nd.adhoc.dmsdk.api.manager.security;

import com.nd.adhoc.dmsdk.api.IDeviceManager;

import java.util.List;

/**
 * 阻止应用被安装
 */
public interface ISecurityManager_DisallowInstall extends IDeviceManager {

    void exec(List list);
}
