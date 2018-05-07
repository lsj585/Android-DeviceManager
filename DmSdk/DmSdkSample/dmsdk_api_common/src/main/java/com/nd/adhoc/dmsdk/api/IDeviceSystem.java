package com.nd.adhoc.dmsdk.api;

import java.util.List;

public interface IDeviceSystem {

    int DEVICE_SYSTEM_CLEARDATA_APP = 1;
    int DEVICE_SYSTEM_CLEARDATA_CACHE = 0;

    void powerOff();

    void sleep();

    void wake();

    void remoteDormancy();

    void remoteApp();

    void getRemoteAppList();

    boolean installApp(String apkFile);

    /**
     * 卸载应用
     *
     * @param packageName
     * @return
     */
    boolean uninstallApp(String packageName);

    void remoteService();

    /**
     * cpu 使用率
     *
     * @param packageName
     * @return
     */
    long getApplicationCpuUsage(String packageName);

    /**
     * ram 使用率
     *
     * @param packageName
     * @return
     */
    long getApplicationRamUsage(String packageName);

    /**
     * 应用程序的数据大小
     *
     * @param packageName
     * @return
     */
    long getApplicationDataSize(String packageName);

    /**
     * 缓存大小
     *
     * @param packageName
     * @return
     */
    long getApplicationCacheSize(String packageName);

    /**
     * 应用程序是否运行中
     *
     * @param packageName
     * @return
     */
    boolean isApplicationRunning(String packageName);

    /**
     * 获取应用程序包名
     *
     * @return
     */
    String[] getAppInstalllist();

    boolean startApp(String packageName, String clsName);

    boolean stopApp(String packageName);

    /**
     * 清除数据
     *
     * @param packageName
     * @return
     */
    boolean wipeData(String packageName);

    /**
     * 不允许清单中的程序运行
     *
     * @param list
     * @return
     */
    boolean unallowRunning(List list);

    /**
     * 允许清单中的程序进入低功耗模式
     *
     * @param list
     * @return
     */
    boolean allowDaemon(List list);

    /**
     * 阻止应用被清除数据
     *
     * @param list
     * @param type
     * @return
     */
    boolean forceClear(List list, int type);

    boolean enableClear(List list, int type);

    /**
     * 阻止应用被卸载
     *
     * @param packageName
     * @return
     */
    boolean forceUnIntsallApp(String packageName);

    /**
     * 允许卸载app
     *
     * @param packageName
     * @return
     */
    boolean enableInstallApp(String packageName);

    /**
     * 允许后台应用杀死
     *
     * @param list
     * @return
     */
    boolean forceDaemon(List list);

    void release();
}
