package com.nd.adhoc.dmsdk.api.manager.security;

import com.nd.adhoc.dmsdk.api.IDeviceManager;

import java.util.List;

/**
 * 阻止应用被进程杀死
 */
public interface SecurityManager_Disallow_Stop extends IDeviceManager {


    void exec(List list);

}
