package com.nd.adhoc.dmsdk.api;

public interface IDeviceControl {
    /**
     * 启用相机
     */
    boolean openCamera();

    /**
     * 禁用相机
     */
    boolean cloaseCamera();

    /**
     * 启用USB
     */
    boolean openUsb();

    /**
     * 禁用USB
     */
    boolean closeUsb();

    /**
     * 启用WIFI
     */
    boolean openWifi();

    /**
     * 禁用WIFI
     */
    boolean closeWifi();

    /**
     * 启用网络
     */
    boolean openNetwork();

    /**
     * 禁用网络
     */
    boolean closeNetwork();

    /**
     * 启用麦克风
     */
    boolean openMicrophone();

    /**
     * 禁用麦克风
     */
    boolean closeMicrophone();

    /**
     * 启用蓝牙
     */
    boolean openBluetooth();

    /**
     * 禁用蓝牙
     */
    boolean closeBluetooth();

    /**
     * 启用sd卡
     */
    boolean openSdCard();

    /**
     * 禁用sd卡
     */
    boolean closeSdCard();

    /**
     * 是否开启camera
     *
     * @return
     */
    boolean isOpenCamrea();

    /**
     * 是否禁用USB
     *
     * @return
     */
    boolean isOpenUsb();

    /**
     * 是否开启蓝牙
     *
     * @return
     */
    boolean isOpenBluetooth();

    /**
     * 是否开启移动网络
     *
     * @return
     */
    boolean isOpenNetwork();

    /**
     * 是否开启麦克风
     *
     * @return
     */
    boolean isOpenMicrophone();

    /**
     * 是否开启WIFI
     *
     * @return
     */
    boolean isOpenWifi();

    /**
     * sd卡是否可用
     *
     * @return
     */
    boolean isOpenSdCard();

    /**
     * 擦除数据
     *
     * @return
     */
    boolean deviceWipeData();

    void release();
}
