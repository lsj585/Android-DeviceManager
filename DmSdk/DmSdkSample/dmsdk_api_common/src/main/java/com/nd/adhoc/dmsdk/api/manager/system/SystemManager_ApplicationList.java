package com.nd.adhoc.dmsdk.api.manager.system;

import com.nd.adhoc.dmsdk.api.IDeviceManager;

import java.util.List;

/**
 * 应用管理
 */
public interface SystemManager_ApplicationList extends IDeviceManager {


    List  getApplicationPakcageList();
}
