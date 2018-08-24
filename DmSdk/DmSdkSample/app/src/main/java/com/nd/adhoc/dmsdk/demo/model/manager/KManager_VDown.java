package com.nd.adhoc.dmsdk.demo.model.manager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;

import com.nd.adhoc.dmsdk.demo.model.manager.base.IDeviceManager;

import static com.nd.adhoc.dmsdk.demo.utils.InputKeyUtils.execKey;

/**
 * @author richsjeson  HOME键
 */

public class KManager_VDown implements IDeviceManager {


    @Override
    public boolean execute(@NonNull Context context) {
        return execKey(KeyEvent.KEYCODE_VOLUME_DOWN);
    }

    @Override
    public boolean isOpen(@NonNull Context context) {
        return false;
    }
}
