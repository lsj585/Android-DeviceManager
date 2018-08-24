package com.nd.adhoc.dmsdk.demo.utils;

import android.app.Instrumentation;

/**
 * @author richsjeson
 * keycode 工具类
 */

public class InputKeyUtils {
    /**
     * 执行key code 指令
     * @param keycode
     * @return
     */
    public static  boolean execKey(int keycode) {
        Instrumentation inst = new Instrumentation();
        inst.sendKeyDownUpSync(keycode);
        return true;
    }
}
