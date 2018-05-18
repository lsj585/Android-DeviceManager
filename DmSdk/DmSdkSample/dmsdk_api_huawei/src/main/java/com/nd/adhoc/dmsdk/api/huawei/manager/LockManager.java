package com.nd.adhoc.dmsdk.api.huawei.manager;
import android.content.Context;

import com.nd.adhoc.dmsdk.api.IDeviceLock;

class LockManager extends BaseManager implements IDeviceLock {



    public void setContext(Context context) {

        super.setContext(context);
    }


    @Override
    public boolean lock() {
        return true;
    }

    @Override
    public boolean unlock() {
        return true;
    }

    /**
     *
     * @return
     */
    public boolean isLock() {
        return true;
    }

    @Override
    public void release() {

    }

    /**
     * 设备锁定时，不允许按键响应
     */
    public void getKeyList(){

    }



}
