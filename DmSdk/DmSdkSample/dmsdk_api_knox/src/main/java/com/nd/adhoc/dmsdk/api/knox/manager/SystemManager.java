package com.nd.adhoc.dmsdk.api.knox.manager;

import android.app.enterprise.ApplicationPolicy;
import android.content.Context;

import com.nd.adhoc.dmsdk.api.IDeviceSystem;

import java.util.List;

class SystemManager extends BaseManager implements IDeviceSystem {
    private ApplicationPolicy applicationPolicy;
    @Override
    public void setContext(Context context) {
        super.setContext(context);
        applicationPolicy=deviceManager.getApplicationPolicy();
    }

    @Override
    public void reboot () {

    }

    @Override
    public void sleep() {

    }

    @Override
    public void wake() {

    }

    @Override
    public void remoteDormancy() {

    }

    @Override
    public void remoteApp() {

    }

    @Override
    public void getRemoteAppList() {

    }

    @Override
    public boolean installApp(String apkFile) {
       return applicationPolicy.installApplication(apkFile,false);
    }

    @Override
    public boolean uninstallApp(String apkName) {
        return applicationPolicy.uninstallApplication(apkName,false);
    }

    @Override
    public void remoteService() {

    }

    @Override
    public long getApplicationCpuUsage(String packageName) {
        return applicationPolicy.getApplicationCpuUsage(packageName);
    }

    @Override
    public long getApplicationRamUsage(String packageName) {
        return applicationPolicy.getApplicationMemoryUsage(packageName);
    }

    @Override
    public long getApplicationDataSize(String packageName) {
        return applicationPolicy.getApplicationDataSize(packageName);
    }

    @Override
    public long getApplicationCacheSize(String packageName) {
        return applicationPolicy.getApplicationCacheSize(packageName);
    }

    @Override
    public boolean isApplicationRunning(String packageName) {
        return applicationPolicy.isApplicationRunning(packageName);
    }

    @Override
    public String[] getAppInstalllist() {
        return applicationPolicy.getInstalledApplicationsIDList();
    }
    @Override
    public boolean startApp(String packageName,String clsName) {
        return applicationPolicy.startApp(packageName,clsName);
    }

    @Override
    public boolean stopApp(String packageName) {
        return applicationPolicy.stopApp(packageName);
    }

    @Override
    public boolean wipeData(String packageName) {
        return applicationPolicy.wipeApplicationData(packageName);
    }

    @Override
    public boolean unallowRunning(List list) {
        List packages=applicationPolicy.addPackagesToPreventStartBlackList(list);
        if(packages!= null && packages.size()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean allowDaemon(List list) {
        //功能无法实现--需要API等级在20.
        //applicationPolicy.addPackageToBatteryOptimizationWhiteList(list);
        //使用防杀策略，加入用户force-stop 黑名单
        return applicationPolicy.addPackagesToForceStopBlackList(list);
    }

    @Override
    public boolean forceClear(List list, int type) {
        if(type==DEVICE_SYSTEM_CLEARDATA_APP){
            return applicationPolicy.addPackagesToClearDataBlackList(list);
        }else{
            return applicationPolicy.addPackagesToClearCacheBlackList(list);
        }
    }

    @Override
    public boolean enableClear(List list, int type) {
        if(type==DEVICE_SYSTEM_CLEARDATA_APP){
            return applicationPolicy.addPackagesToClearDataWhiteList(list);
        }else{
            return applicationPolicy.addPackagesToClearCacheWhiteList(list);
        }
    }

    @Override
    public boolean forceUnIntsallApp(String packageName) {
         applicationPolicy.setApplicationUninstallationDisabled(packageName);
         return true;
    }

    @Override
    public boolean enableInstallApp(String packageName) {
        applicationPolicy.setApplicationUninstallationEnabled(packageName);
        return false;
    }

    @Override
    public boolean forceDaemon(List list) {
        return applicationPolicy.addPackagesToForceStopWhiteList(list);
    }

    @Override
    public void release() {
        applicationPolicy=null;
    }
}
