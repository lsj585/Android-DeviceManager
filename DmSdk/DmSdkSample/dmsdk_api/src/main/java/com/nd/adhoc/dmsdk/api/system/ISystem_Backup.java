package com.nd.adhoc.dmsdk.api.system;

import com.nd.adhoc.dmsdk.IDmSdkApi;

/**
 * 系统备份
 */
public interface ISystem_Backup extends IDmSdkApi {
    /**
     * 执行系统备份的操作
     */
    boolean execBackup();
}
