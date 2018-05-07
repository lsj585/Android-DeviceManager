package com.nd.adhoc.dmsdk.api;

public interface IDeviceKey {

	boolean remoteVolumnUpKey();

	boolean remoteVolumnDownKey();

	boolean remoteVolumnKey(int number);

	boolean remoteHomeKey();

	boolean remoteBackKey();

	boolean remoteBrightness(int number);

	/**
	 * 电源键
	 * @return
	 */
	boolean remotePowerKey();

	/**
	 * 唤醒
	 */
	boolean remoteWake();

	/**
	 * 休眠
	 * @return
	 */
	boolean remoteDormancyKey();

	/**
	 * 重启
	 * @return
	 */
	boolean remoteRebootKey();

	/**
	 * 关机
	 * @return
	 */
	boolean remotePowerOffKey();

    void release();
}
