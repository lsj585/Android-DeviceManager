package com.nd.adhoc.dmsdk.api.hardware.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;

/**
 *@
 */
public interface IHardware_Close extends IDmSdkApi {
    /**
     * 关闭
     */
    boolean close(@NonNull Context context);
}
