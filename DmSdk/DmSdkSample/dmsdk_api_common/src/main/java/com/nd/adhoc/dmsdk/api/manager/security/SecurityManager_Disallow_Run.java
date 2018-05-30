package com.nd.adhoc.dmsdk.api.manager.security;

import com.nd.adhoc.dmsdk.api.IDeviceManager;

import java.util.List;

/**
 * 阻止应用运行
 */
public interface SecurityManager_Disallow_Run extends IDeviceManager {

    void exec(List list);
}
