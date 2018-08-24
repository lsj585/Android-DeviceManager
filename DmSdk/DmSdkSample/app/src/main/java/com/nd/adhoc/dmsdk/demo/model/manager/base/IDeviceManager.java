package com.nd.adhoc.dmsdk.demo.model.manager.base;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * @author richsjeson
 */

public interface IDeviceManager {

     boolean execute(@NonNull Context context);

     boolean isOpen(@NonNull Context context);

}
