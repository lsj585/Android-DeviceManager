package com.nd.adhoc.dmsdk.api.huawei;

import android.content.Context;

import com.nd.adhoc.dmsdk.api.IDmApiManager;
import com.nd.adhoc.dmsdk.api.huawei.manager.DeviceSdkApiManager;

import java.util.List;

public abstract class IDmSdkApiManager implements IDmApiManager {


    private static IDmSdkApiManager instance = null;


    public synchronized static <T extends IDmSdkApiManager> T getInstance(Context context) {
        synchronized (IDmSdkApiManager.class) {
            if (instance == null) {//二次检查
                instance = new DeviceSdkApiManager(context);
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

    /**
     * 清除数据
     * @param packageName
     * @return
     */
    public abstract boolean wipeData(String packageName);

    /**
     * 不允许运行
     * @param list
     * @return
     */
    public abstract boolean addToUnRunningAppList(List list);

    /**
     * 防止应用被用户主动杀死
     * @param
     * @return
     */
    public abstract boolean allowDaemon(List list,boolean isStop);

    /**
     * 允许/禁止清除应用数据
     * @param list
     * @param type 0 应用数据 1 缓存数据
     * @return
     */
    public abstract boolean clearDataFromApp(List list, int type,boolean isClearData);

    /**
     * 允许/卸载应用策略
     * @param packageName
     * @return isUninstall 是否卸载 true 允许卸载 false 不允许卸载
     */
    public abstract boolean unInstallApp(String packageName,boolean isUninstall);

    /**
     * 恢复出产设置 该方法风险较大，建议慎用
     * @return
     */
    public abstract boolean restorFactory();
}
