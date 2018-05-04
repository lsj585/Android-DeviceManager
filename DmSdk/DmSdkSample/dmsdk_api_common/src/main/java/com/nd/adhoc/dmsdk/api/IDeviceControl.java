package com.nd.adhoc.dmsdk.api;

public interface IDeviceControl  {
	/**
	 * 启用相机
	 */
	public boolean openCamera();
	/**
	 * 禁用相机
	 */
	public boolean cloaseCamera();
	/**
	 * 启用USB
	 */
	public boolean openUsb();
	/**
	 * 禁用USB
	 */
	public boolean closeUsb();
	/**
	 * 启用WIFI
	 */
	public boolean openWifi();
	/**
	 * 禁用WIFI
	 */
	public boolean closeWifi();
	/**
	 * 启用网络
	 */
	public boolean openNetwork();
	/**
	 * 禁用网络
	 */
	public boolean closeNetwork();
	/**
	 * 启用4G LTE
	 */
	public boolean openLTE();
	/**
	 * 禁用4G LTE
	 */
	public boolean closeLTE();
	/**
	 * 启用麦克风
	 */
	public boolean openMicrophone();
	/**
	 * 禁用麦克风
	 */
	public boolean closeMicrophone();

	/**
	 * 启用蓝牙
	 */
	public boolean openBluetooth();
	/**
	 * 禁用蓝牙
	 */
	public boolean closeBluetooth();
	/**
	 * 启用sd卡
	 */
	public boolean openSdCard();
	/**
	 *禁用sd卡
	 */
	public boolean closeSdCard();

	/**
	 * 是否开启camera
	 * @return
	 */
	boolean isOpenCamrea();

	/**
	 * 是否禁用USB
	 * @return
	 */
	boolean isOpenUsb();

	/**
	 * 是否开启蓝牙
	 * @return
	 */
	boolean isOpenBluetooth();

	/**
	 * 是否开启移动网络
	 * @return
	 */
	boolean isOpenNetwork();

	/**
	 * 是否开启麦克风
	 * @return
	 */
	boolean isOpenMicrophone();

	/**
	 * 是否开启麦克风
	 * @return
	 */
	boolean isOpenLTE();

	/**
	 * 是否开启WIFI
	 * @return
	 */
	boolean isOpenWifi();

	/**
	 * sd卡是否可用
	 * @return
	 */
    boolean isOpenSdCard();
}
