package com.nd.adhoc.dmsdk.demo.strategy;

import com.nd.adhoc.dmsdk.demo.strategy.file.GetFileListStrategy;
import com.nd.adhoc.dmsdk.demo.strategy.file.InstallApkStrategy;

/**
 * 应用管理类
 */
public class ApkFireFactory {

    /**
     * 卸载应用-策略
     */
    private GetFileListStrategy mGetFileListStrategy;
    /**
     * 阻止卸载应用-策略
     */
    private InstallApkStrategy mInstallApkStrategy;

    /**
     * 应用卸载
     */
    public static final int STRATEGY_FILE_GETLIST = 0;
    public static final int STRATEGY_FILE_INSTALLAPK = 1;

    /**
     * 产品目标对象
     *
     * @param position
     * @return
     */
    public FileControlStrategy getStrategy(int position) {

        switch (position) {
            case STRATEGY_FILE_GETLIST:
                if (mGetFileListStrategy == null) {
                    return mGetFileListStrategy = new GetFileListStrategy();
                }
                return mGetFileListStrategy;
            case STRATEGY_FILE_INSTALLAPK:
                if (mInstallApkStrategy == null) {
                    return mInstallApkStrategy = new InstallApkStrategy();
                }
                return mInstallApkStrategy;
            default:
                if (mGetFileListStrategy == null) {
                    return mGetFileListStrategy = new GetFileListStrategy();
                }
                return mGetFileListStrategy;
        }
    }
}
