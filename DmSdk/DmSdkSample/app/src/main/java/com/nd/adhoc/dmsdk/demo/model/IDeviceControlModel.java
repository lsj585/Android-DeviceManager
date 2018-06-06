package com.nd.adhoc.dmsdk.demo.model;
/**
 * 设备控制类
 */
public interface IDeviceControlModel{
    /**
     * 更新状态
     */
    boolean updateStatus(int position) throws Exception;

    /**
     * 更新状态是否成功
     * @param position
     * @param isSuccess 是否执行成功
     * @return
     */
    void update(int position,boolean isSuccess);

}
