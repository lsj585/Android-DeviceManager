package com.nd.adhoc.dmsdk.demo.model.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.api.key.IPhysicalKey_Menu;
import com.nd.adhoc.dmsdk.demo.model.manager.base.IDeviceManager;
import com.nd.adhoc.dmsdk.demo.utils.InputKeyUtils;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;

import static com.nd.adhoc.dmsdk.demo.utils.InputKeyUtils.execKey;

/**
 * @author richsjeson  HOME键
 */

public class KManager_Menu implements IDeviceManager {


    @Override
    public boolean execute(@NonNull Context context) {
        return execKey(KeyEvent.KEYCODE_MENU);
    }

    @Override
    public boolean isOpen(@NonNull Context context) {
        return false;
    }
}
