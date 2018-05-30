package com.nd.adhoc.dmsdk.api.manager.security;

import com.nd.adhoc.dmsdk.api.IDeviceManager;

import java.util.List;

/**
 * 应用缓存清理
 */
public interface ISecurityManager_DisallowWipeData extends IDeviceManager{

    void exec(List list);
}
