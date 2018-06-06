package com.nd.adhoc.dmsdk.demo.strategy;

import com.nd.adhoc.dmsdk.demo.strategy.app.AllowStartApplicationApplicationStrategy;
import com.nd.adhoc.dmsdk.demo.strategy.app.AllowStopApplicationApplicationStrategy;
import com.nd.adhoc.dmsdk.demo.strategy.app.AllowUninstallApplicationApplicationStrategy;
import com.nd.adhoc.dmsdk.demo.strategy.app.AllowWipeDataApplicationApplicationStrategy;
import com.nd.adhoc.dmsdk.demo.strategy.app.ForbridStopApplicationApplicationStrategy;
import com.nd.adhoc.dmsdk.demo.strategy.app.ForbridUnIntallApplicationApplicationStrategy;
import com.nd.adhoc.dmsdk.demo.strategy.app.ForbridStartApplicationApplicationStrategy;
import com.nd.adhoc.dmsdk.demo.strategy.app.GetApplicationListApplicationStrategy;
import com.nd.adhoc.dmsdk.demo.strategy.app.StartApplicationApplicationStrategy;
import com.nd.adhoc.dmsdk.demo.strategy.app.StopApplicationApplicationStrategy;
import com.nd.adhoc.dmsdk.demo.strategy.app.UninstallApplicationApplicationStrategy;
import com.nd.adhoc.dmsdk.demo.strategy.app.WipeDataApplicationApplicationStrategy;

/**
 * 应用管理类
 */
public class ApplicationManagerFactory {

    /**
     * 卸载应用-策略
     */
    private ApplicationStrategy mUninstallApplicationApplicationStrategy;
    /**
     * 阻止卸载应用-策略
     */
    private ApplicationStrategy mForbridUnIntallApplicationApplicationStrategy;
    /**
     * 应用数据清理-策略
     */
    private ApplicationStrategy mWipeDataApplicationApplicationStrategy;
    /**
     * 阻止应用被启动-策略
     */
    private ApplicationStrategy mForbridStartApplicationApplicationStrategy;
    /**
     * 允许应用被停止-策略
     */
    private ApplicationStrategy mAllowStopApplicationApplicationStrategy;
    /**
     * 启动应用-策略
     */
    private ApplicationStrategy mStartApplicationApplicationStrategy;
    /**
     * 停止应用-策略
     */
    private ApplicationStrategy mStopApplicationApplicationStrategy;
    /**
     * 阻止应用被停止-策略
     */
    private ApplicationStrategy mForbridStopApplicationApplicationStrategy;
    /**
     * 允许应用被启动-策略
     */
    private ApplicationStrategy mAllowStartApplicationApplicationStrategy;
    /**
     * 允许应用数据被清理-策略
     */
    private ApplicationStrategy mAllowWipeDataApplicationApplicationStrategy;
    /**
     * 允许应用数据被卸载-策略
     */
    private ApplicationStrategy mAllowUninstallApplicationApplicationStrategy;
    /**
     * 获取应用列表-策略
     */
    private ApplicationStrategy mGetApplicationListApplicationStrategy;

    /**
     * 应用卸载
     */
    public static final int STRATEGY_UNINSTALL=0;
    public static final int STRATEGY_FORBIDUNINSTALL=1;
    public static final int STRATEGY_WIPEDATA=2;
    public static final int STRATEGY_FORBRID_STARTAPP=3;
    public static final int STRATEGY_FORBRID_STOPAPP=4;
    public static final int STRATEGY_START=5;
    public static final int STRATEGY_STOP=6;
    public static final int STRATEGY_FORBRID_STOPAPSP=7;
    public static final int STRATEGY_ALLOW_START=8;
    public static final int STRATEGY_ALLOW_WIPEDATA=9;
    public static final int STRATEGY_ALLOW_UNINSTALL=10;
    public static final int STRATEGY_GET_APPLIST=11;

    /**
     * 产品目标对象
     *
     * @param position
     * @return
     */
    public ApplicationStrategy getStrategy(int position) {

        switch (position) {
            case STRATEGY_UNINSTALL:
                if (mUninstallApplicationApplicationStrategy == null) {
                    return mUninstallApplicationApplicationStrategy = new UninstallApplicationApplicationStrategy();
                }
                return mUninstallApplicationApplicationStrategy;
            case STRATEGY_FORBIDUNINSTALL:
                if (mForbridUnIntallApplicationApplicationStrategy == null) {
                    return mForbridUnIntallApplicationApplicationStrategy = new ForbridUnIntallApplicationApplicationStrategy();
                }
                return mForbridUnIntallApplicationApplicationStrategy;
            case STRATEGY_WIPEDATA:
                if (mWipeDataApplicationApplicationStrategy == null) {
                    return mWipeDataApplicationApplicationStrategy = new WipeDataApplicationApplicationStrategy();
                }
                return mWipeDataApplicationApplicationStrategy;
            case STRATEGY_FORBRID_STARTAPP:
                if (mForbridStartApplicationApplicationStrategy == null) {
                    return mForbridStartApplicationApplicationStrategy = new ForbridStartApplicationApplicationStrategy();
                }
                return mForbridStartApplicationApplicationStrategy;
            case STRATEGY_FORBRID_STOPAPP:
                if (mAllowStopApplicationApplicationStrategy == null) {
                    return mAllowStopApplicationApplicationStrategy = new AllowStopApplicationApplicationStrategy();
                }
                return mAllowStopApplicationApplicationStrategy;
            case STRATEGY_START:
                if (mStartApplicationApplicationStrategy == null) {
                    return mStartApplicationApplicationStrategy = new StartApplicationApplicationStrategy();
                }
                return mStartApplicationApplicationStrategy;
            case STRATEGY_STOP:
                if (mStopApplicationApplicationStrategy == null) {
                    return mStopApplicationApplicationStrategy = new StopApplicationApplicationStrategy();
                }
                return mStopApplicationApplicationStrategy;
            case STRATEGY_FORBRID_STOPAPSP:
                if (mForbridStopApplicationApplicationStrategy == null) {
                    return mForbridStopApplicationApplicationStrategy = new ForbridStopApplicationApplicationStrategy();
                }
                return mStopApplicationApplicationStrategy;
            case STRATEGY_ALLOW_START:
                if (mForbridStartApplicationApplicationStrategy == null) {
                    return mForbridStartApplicationApplicationStrategy = new ForbridStartApplicationApplicationStrategy();
                }
                return mForbridStartApplicationApplicationStrategy;
            case STRATEGY_ALLOW_WIPEDATA:
                if (mAllowStartApplicationApplicationStrategy == null) {
                    return mAllowStartApplicationApplicationStrategy = new AllowStartApplicationApplicationStrategy();
                }
                return mAllowStartApplicationApplicationStrategy;
            case STRATEGY_ALLOW_UNINSTALL:
                if (mAllowWipeDataApplicationApplicationStrategy == null) {
                    return mAllowWipeDataApplicationApplicationStrategy = new AllowWipeDataApplicationApplicationStrategy();
                }
                return mAllowWipeDataApplicationApplicationStrategy;
            case STRATEGY_GET_APPLIST:
                if (mAllowUninstallApplicationApplicationStrategy == null) {
                    return mAllowUninstallApplicationApplicationStrategy = new AllowUninstallApplicationApplicationStrategy();
                }
                return mAllowUninstallApplicationApplicationStrategy;
            default:
                if (mGetApplicationListApplicationStrategy == null) {
                    return mGetApplicationListApplicationStrategy = new GetApplicationListApplicationStrategy();
                }
                return mAllowUninstallApplicationApplicationStrategy;
        }
    }
}
