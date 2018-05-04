package com.nd.adhoc.dmsdk.api;

public interface IDmApiManager {

    /**
     * 设备锁定
     *
     * @param isLock
     */
    void deviceLock(boolean isLock);

    /**
     * 关机
     */
    void deivcePowerOff();

    /**
     * 摄像头
     * @param isOpen
     */
    void deviceCamera(boolean isOpen);
    /**
     * usb功能
     * @param isOpen
     */
    void deviceUsb(boolean isOpen);
    /**
     * 麦克风
     * @param isOpen
     */
    void deviceMicrophone(boolean isOpen);
    /**
     * wifi
     * @param isOpen
     */
    void deviceWifi(boolean isOpen);

    /**
     * 4G TLE
     * @param isOpen
     */
    void deviceLTE(boolean isOpen);

    /**
     * 2，5 G 移动网络
     * @param isOpen
     */
    void deviceMobileNetwork(boolean isOpen);

    /**
     * 激活设备
     */
    void activeLicense();

}
