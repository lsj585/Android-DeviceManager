package com.adhoc.dmsdk.api;

/**
 * 执行的回调
 */
public interface DmExecCallBack {

     void onSuccess(int status);

     void onFailure();
}
