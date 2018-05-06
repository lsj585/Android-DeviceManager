package com.nd.adhoc.dmsdk.demo.model;
/**
 * 设备控制类
 */
public interface IAppManagerModel {
    /**
     * 启动应用
     * @param position
     * @return
     */
    boolean startApp(int position);

    /**
     * 停止应用
     * @param position
     * @return
     */
    boolean stopApp(int position);

    /**
     * 清除数据
     * @param position
     * @return
     */
    boolean wipeData(int position);

    /**
     * 更新状态是否成功
     * @param position
     * @param isSuccess 是否执行成功
     * @return
     */
    void update(int position,boolean isSuccess);

    /**
     * 数据清理后的回调
     * @param position
     */
    void updateWipeStatus(int position);
    /**
     * 卸载APP
     * @param position
     */
    boolean uninstallApp(int position);
}
