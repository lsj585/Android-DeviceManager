package com.nd.adhoc.dmsdk.api.screen;

import com.nd.adhoc.dmsdk.IDmSdkApi;

/**
 * 系统屏保密码设置
 */
public interface IScreen_Passwd extends IDmSdkApi {
    /**
     * 设置屏保的密码
     * @param passwd 密码
     */
    void exec(String passwd);
}
