package com.nd.adhoc.dmsdk.api.system;

import com.nd.adhoc.dmsdk.IDmSdkApi;

/**
 * @author richsjeson
 * 设置音量大小
 */
public interface ISystem_Volumn extends IDmSdkApi {
    /**
     * 设置音量大小
     * @param count
     */
    void executeVolumnCount(int count);
}
