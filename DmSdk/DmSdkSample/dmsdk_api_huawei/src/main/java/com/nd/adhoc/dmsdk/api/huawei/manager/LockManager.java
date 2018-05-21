package com.nd.adhoc.dmsdk.api.huawei.manager;
import android.content.Context;

import com.huawei.android.app.admin.DeviceRestrictionManager;
import com.nd.adhoc.dmsdk.api.IDeviceLock;

class LockManager extends BaseManager implements IDeviceLock {

    private DeviceRestrictionManager deviceRestrictionManager;



    public void setContext(Context context) {
        super.setContext(context);
        deviceRestrictionManager=new DeviceRestrictionManager();
    }

    @Override
    public boolean lock() {
        //HOME键
        deviceRestrictionManager.setHomeButtonDisabled(getComponentName(),true);
        //返回键
        deviceRestrictionManager.setBackButtonDisabled(getComponentName(),true);
        //任务键
        deviceRestrictionManager.setTaskButtonDisabled(getComponentName(),true);

        return true;
    }

    @Override
    public boolean unlock() {
        //HOME键
        deviceRestrictionManager.setHomeButtonDisabled(getComponentName(),false);
        //返回键
        deviceRestrictionManager.setBackButtonDisabled(getComponentName(),false);
        //任务键
        deviceRestrictionManager.setTaskButtonDisabled(getComponentName(),false);
        return true;
    }

    /**
     *
     * @return
     */
    public boolean isLock() {
       boolean isBack= deviceRestrictionManager.isBackButtonDisabled(getComponentName());
       boolean isHome= deviceRestrictionManager.isHomeButtonDisabled(getComponentName());
       boolean isTask= deviceRestrictionManager.isTaskButtonDisabled(getComponentName());
       return  isBack&&isHome&&isTask?true:false;
    }

    @Override
    public void release() {
        deviceRestrictionManager=null;
    }
}
