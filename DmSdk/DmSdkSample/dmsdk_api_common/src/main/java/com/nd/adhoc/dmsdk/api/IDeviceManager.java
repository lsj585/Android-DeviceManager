package com.nd.adhoc.dmsdk.api;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 管理器的回收
 */
public interface IDeviceManager {

    void release(@NonNull Context context);
}
