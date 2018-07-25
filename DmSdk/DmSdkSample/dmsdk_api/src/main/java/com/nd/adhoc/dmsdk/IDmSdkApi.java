package com.nd.adhoc.dmsdk;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * 管理器的回收
 */
public interface IDmSdkApi {

    void release(@NonNull Context context);
}
