package com.nd.adhoc.dmsdk.demo.bean;

import android.graphics.drawable.Drawable;

/**
 * 应用列表状态
 */
public class ApplicationInfoBean extends BaseBean {
    /**
     * 应用包名
     */
    private String packageName;
    /**
     * 应用的启动入口类
     */
    private String launcherName;
    /**
     * 应用名称
     */
    private String name;
    /**
     * 应用是否运行
     */
    private boolean isRunning;
    /**
     * 应用状态 0 黑名单 1白名单
     */
    private int status;
    /**
     * cpu 使用率
     */
    private long cpuUsage;
    /**
     * 内存使用率
     */
    private long ramUsage;
    /**
     * 应用数据
     */
    private long applicationDataSizeUsage;
    /**
     * 应用缓存
     */
    private long applicationCacheSizeUsage;


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLauncherName() {
        return launcherName;
    }

    public void setLauncherName(String launcherName) {
        this.launcherName = launcherName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(long cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public long getRamUsage() {
        return ramUsage;
    }

    public void setRamUsage(long ramUsage) {
        this.ramUsage = ramUsage;
    }

    public long getApplicationDataSizeUsage() {
        return applicationDataSizeUsage;
    }

    public void setApplicationDataSizeUsage(long applicationDataSizeUsage) {
        this.applicationDataSizeUsage = applicationDataSizeUsage;
    }

    public long getApplicationCacheSizeUsage() {
        return applicationCacheSizeUsage;
    }

    public void setApplicationCacheSizeUsage(long applicationCacheSizeUsage) {
        this.applicationCacheSizeUsage = applicationCacheSizeUsage;
    }
}
