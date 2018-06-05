package com.nd.adhoc.dmsdk.demo.bean;

/**
 * 应用列表状态
 */
public class ApplicationInfoBean extends BaseBean {
    /**
     * 应用包名
     */
    private String mPackageName;
    /**
     * 应用的启动入口类
     */
    private String mLauncherName;
    /**
     * 应用名称
     */
    private String mName;
    /**
     * 应用状态 0 黑名单 1白名单
     */
    private int mStatus;
    /**
     * cpu 使用率
     */
    private long mCpuUsage;
    /**
     * 内存使用率
     */
    private long mRamUsage;
    /**
     * 应用数据
     */
    private long mApplicationDataSizeUsage;
    /**
     * 应用缓存
     */
    private long mApplicationCacheSizeUsage;
    /**
     * 允许卸载
     */
    private boolean mAllowUninstall;
    /**
     * 允许清除数据
     */
    private boolean mAllowClearData;
    /**
     * 允许运行
     */
    private boolean mAllowRunning;
    /**
     * 允许停止APP
     */
    private boolean mAllowStopApp;


    public String getPackageName() {
        return mPackageName;
    }

    public void setPackageName(String packageName) {
        this.mPackageName = packageName;
    }

    public String getLauncherName() {
        return mLauncherName;
    }

    public void setLauncherName(String launcherName) {
        this.mLauncherName = launcherName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        this.mStatus = status;
    }

    public long getCpuUsage() {
        return mCpuUsage;
    }

    public void setCpuUsage(long cpuUsage) {
        this.mCpuUsage = cpuUsage;
    }

    public long getRamUsage() {
        return mRamUsage;
    }

    public void setRamUsage(long ramUsage) {
        this.mRamUsage = ramUsage;
    }

    public long getApplicationDataSizeUsage() {
        return mApplicationDataSizeUsage;
    }

    public void setApplicationDataSizeUsage(long applicationDataSizeUsage) {
        this.mApplicationDataSizeUsage = applicationDataSizeUsage;
    }

    public long getApplicationCacheSizeUsage() {
        return mApplicationCacheSizeUsage;
    }

    public void setApplicationCacheSizeUsage(long applicationCacheSizeUsage) {
        this.mApplicationCacheSizeUsage = applicationCacheSizeUsage;
    }

    public boolean isAllowUninstall() {
        return mAllowUninstall;
    }

    public void setAllowUninstall(boolean allowUninstall) {
        this.mAllowUninstall = allowUninstall;
    }

    public boolean isAllowClearData() {
        return mAllowClearData;
    }

    public void setAllowClearData(boolean allowClearData) {
        this.mAllowClearData = allowClearData;
    }

    public boolean isAllowRunning() {
        return mAllowRunning;
    }

    public void setAllowRunning(boolean allowRunning) {
        this.mAllowRunning = allowRunning;
    }

    public boolean isAllowStopApp() {
        return mAllowStopApp;
    }

    public void setAllowStopApp(boolean allowStopApp) {
        this.mAllowStopApp = allowStopApp;
    }
}
