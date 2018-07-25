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

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
    public static final int STRATEGY_UNINSTALL = 0;
    public static final int STRATEGY_FORBIDUNINSTALL = 1;
    public static final int STRATEGY_WIPEDATA = 2;
    public static final int STRATEGY_FORBRID_STARTAPP = 3;
    public static final int STRATEGY_ALLOW_STOPAPP = 4;
    public static final int STRATEGY_START = 5;
    public static final int STRATEGY_STOP = 6;
    public static final int STRATEGY_FORBRID_STOPAPSP = 7;
    public static final int STRATEGY_ALLOW_START = 8;
    public static final int STRATEGY_ALLOW_WIPEDATA = 9;
    public static final int STRATEGY_ALLOW_UNINSTALL = 10;
    public static final int STRATEGY_GET_APPLIST = 11;


    private Map<Integer, ApplicationStrategy> mStrategys = new ConcurrentHashMap();


    public ApplicationManagerFactory() {

        if (mUninstallApplicationApplicationStrategy == null) {
            mUninstallApplicationApplicationStrategy = new UninstallApplicationApplicationStrategy();
        }

        mStrategys.put(STRATEGY_UNINSTALL, mUninstallApplicationApplicationStrategy);

        if (mForbridUnIntallApplicationApplicationStrategy == null) {

             mForbridUnIntallApplicationApplicationStrategy = new ForbridUnIntallApplicationApplicationStrategy();
        }

        mStrategys.put(STRATEGY_FORBIDUNINSTALL, mForbridUnIntallApplicationApplicationStrategy);

        if (mWipeDataApplicationApplicationStrategy == null) {

             mWipeDataApplicationApplicationStrategy = new WipeDataApplicationApplicationStrategy();
        }
        mStrategys.put(STRATEGY_WIPEDATA, mWipeDataApplicationApplicationStrategy);

        if (mForbridStartApplicationApplicationStrategy == null) {
             mForbridStartApplicationApplicationStrategy = new ForbridStartApplicationApplicationStrategy();
        }

        mStrategys.put(STRATEGY_FORBRID_STARTAPP, mForbridStartApplicationApplicationStrategy);

        if (mAllowStopApplicationApplicationStrategy == null) {
             mAllowStopApplicationApplicationStrategy = new AllowStopApplicationApplicationStrategy();
        }
        mStrategys.put(STRATEGY_ALLOW_STOPAPP, mAllowStopApplicationApplicationStrategy);


        if (mStartApplicationApplicationStrategy == null) {
             mStartApplicationApplicationStrategy = new StartApplicationApplicationStrategy();
        }

        mStrategys.put(STRATEGY_START, mStartApplicationApplicationStrategy);

        if (mStopApplicationApplicationStrategy == null) {
            mStopApplicationApplicationStrategy = new StopApplicationApplicationStrategy();
        }

        mStrategys.put(STRATEGY_STOP, mStopApplicationApplicationStrategy);


        if (mForbridStopApplicationApplicationStrategy == null) {
            mForbridStopApplicationApplicationStrategy = new ForbridStopApplicationApplicationStrategy();
        }

        mStrategys.put(STRATEGY_FORBRID_STOPAPSP, mForbridStopApplicationApplicationStrategy);


        if (mAllowStartApplicationApplicationStrategy == null) {
             mAllowStartApplicationApplicationStrategy = new AllowStartApplicationApplicationStrategy();
        }
        mStrategys.put(STRATEGY_ALLOW_START, mAllowStartApplicationApplicationStrategy);

        if (mAllowWipeDataApplicationApplicationStrategy == null) {
            mAllowWipeDataApplicationApplicationStrategy = new AllowWipeDataApplicationApplicationStrategy();
        }

        mStrategys.put(STRATEGY_ALLOW_WIPEDATA, mAllowWipeDataApplicationApplicationStrategy);

        if (mAllowUninstallApplicationApplicationStrategy == null) {
            mAllowUninstallApplicationApplicationStrategy = new AllowUninstallApplicationApplicationStrategy();
        }

        mStrategys.put(STRATEGY_ALLOW_UNINSTALL, mAllowUninstallApplicationApplicationStrategy);


        if (mGetApplicationListApplicationStrategy == null) {
             mGetApplicationListApplicationStrategy = new GetApplicationListApplicationStrategy();
        }
        mStrategys.put(STRATEGY_GET_APPLIST, mGetApplicationListApplicationStrategy);
    }


    /**
     * 产品目标对象
     *
     * @param position
     * @return
     */
    public ApplicationStrategy getStrategy(int position) {

        return mStrategys.get(position);
    }

}
