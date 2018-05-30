package com.nd.adhoc.dmsdk.api.manager.hardware;

import com.nd.adhoc.dmsdk.api.IDeviceManager;

/**
 *@
 */
public interface IHardwareOperation extends IDeviceManager {
    /**
     * 开启
     */
     void open();
    /**
     * 关闭
     */
    void close();

    /**
     * 判断是否打开
     * @return
     */
     boolean isOpen();
}
