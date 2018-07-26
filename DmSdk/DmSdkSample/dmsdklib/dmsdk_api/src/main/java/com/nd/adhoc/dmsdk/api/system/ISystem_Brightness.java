package com.nd.adhoc.dmsdk.api.system;

import com.nd.adhoc.dmsdk.IDmSdkApi;

/**
 * 亮度设置API接口
 */
public interface ISystem_Brightness extends IDmSdkApi {
    /**
     * 设置亮度大小
     * @param brightness
     */
    void execBrightness(int brightness);
}
