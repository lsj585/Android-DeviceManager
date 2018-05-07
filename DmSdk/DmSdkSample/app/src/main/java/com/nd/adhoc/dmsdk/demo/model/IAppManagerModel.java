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

    /**
     * 不允许运行此应用
     * @param position
     * @return
     */
    boolean unallowRunning(int position);

    /**
     * 允许后台守护
     * @param position
     * @return
     */
    boolean allowDaemon(int position);

    /**
     * 阻止应用数据被清除
     * @param viewPosition
     * @return
     */
    boolean forceClearData(int viewPosition);

    /**
     * 清理数据
     * @param viewPosition
     */
    void updateToClearData(int viewPosition,boolean isClearData);

    /**
     * 运行
     * @param viewPosition
     * @param isRunning
     */
    void updateToRunning(int viewPosition,boolean isRunning);

    /**
     * 卸载
     * @param viewPosition
     * @param isUninstall
     */
    void updateToUninstall(int viewPosition,boolean isUninstall);

    /**
     * 停止应用
     * @param viewPosition
     * @param isStop
     */
    void updateToStopApp(int viewPosition,boolean isStop);

    /**
     * 阻止应用卸载
     * @param viewPosition
     * @return
     */
    boolean forceUnInstall(int viewPosition);

    /**
     * 允许运行APP
     * @param viewPosition
     * @return
     */
    boolean allowRunApp(int viewPosition);
    /**
     * 允许App清除数据
     * @param viewPosition
     * @return
     */
    boolean allowClearData(int viewPosition);

    /**
     * 允许卸载APP
     * @param viewPosition
     * @return
     */
    boolean allowUninstall(int viewPosition);
}
