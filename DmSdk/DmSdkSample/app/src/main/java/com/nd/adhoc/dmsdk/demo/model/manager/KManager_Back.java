package com.nd.adhoc.dmsdk.demo.model.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.nd.adhoc.dmsdk.demo.model.manager.base.IDeviceManager;
import com.nd.adhoc.dmsdk.demo.utils.InputKeyUtils;

/**
 * @author richsjeson  HOME键
 */

public class KManager_Back implements IDeviceManager {


    @Override
    public boolean execute(@NonNull Context context) {
        return InputKeyUtils.execKey(KeyEvent.KEYCODE_BACK);
    }

    @Override
    public boolean isOpen(@NonNull Context context) {
        return false;
    }
}
