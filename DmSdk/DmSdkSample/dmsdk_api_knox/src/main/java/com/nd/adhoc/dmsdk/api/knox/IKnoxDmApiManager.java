package com.nd.adhoc.dmsdk.api.knox;

import android.content.Context;

import com.nd.adhoc.dmsdk.api.IDmApiManager;
import com.nd.adhoc.dmsdk.api.knox.manager.DeviceApiManager;

public abstract class IKnoxDmApiManager{


    private static IKnoxDmApiManager instance = null;


    public synchronized static <T extends IKnoxDmApiManager> T getInstance(Context context) {
        synchronized (IKnoxDmApiManager.class) {
            if (instance == null) {//二次检查
                instance = new DeviceApiManager(context);
            }
        }
        return (T)instance;
    }

    /**
     * 设备锁定
     */
    public abstract boolean deviceLock();

    /**
     * 关机
     */
    public abstract void deivcePowerOff();

    /**
     * 摄像头
     *
     */
    public abstract boolean deviceCamera();
    /**
     * usb功能
     *
     */
    public abstract boolean deviceUsb();
    /**
     * 麦克风
     *
     */
    public abstract boolean deviceMicrophone();
    /**
     * wifi
     *
     */
    public abstract boolean deviceWifi();

    /**
     * 4G TLE
     *
     */
    public abstract boolean deviceLTE();

    /**
     * 2，5 G 移动网络
     *
     */
    public abstract boolean deviceMobileNetwork();

    /**
     * 激活设备
     */
    public abstract void activeLicense();

    /**
     * 蓝牙
     */
    public abstract boolean deviceBluetooth();

    /**
     * sd卡
     */
    public abstract boolean deviceSdCard();

    /**
     * 设备是否是否已锁
     * @return
     */
    public abstract boolean isLock();


    public void release(){
        instance=null;
    }

    /**
     * 启动应用
     * @param launcherName
     * @return
     */
    public abstract boolean startApp(String packageName,String launcherName);

    /**
     * 终止应用
     * @param
     * @return
     */
    public abstract boolean stopApp(String packageName);
}
