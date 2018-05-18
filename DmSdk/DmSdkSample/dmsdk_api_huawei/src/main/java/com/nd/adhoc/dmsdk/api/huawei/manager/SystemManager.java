package com.nd.adhoc.dmsdk.api.huawei.manager;
import android.content.Context;

import com.huawei.android.app.admin.DeviceControlManager;
import com.nd.adhoc.dmsdk.api.IDeviceSystem;

import java.util.List;

class SystemManager extends BaseManager implements IDeviceSystem {


    private DeviceControlManager deviceControlManager;

    @Override
    public void setContext(Context context) {
        super.setContext(context);
        deviceControlManager=new DeviceControlManager();
    }

    @Override
    public void powerOff() {
        deviceControlManager.shutdownDevice(getComponentName());
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
    public void reboot() {
        deviceControlManager.rebootDevice(getComponentName());
    }

    @Override
    public boolean installApp(String apkFile) {
        return true;
    }

    @Override
    public boolean uninstallApp(String apkName) {
        return true;
    }

    @Override
    public void remoteService() {

    }

    @Override
    public long getApplicationCpuUsage(String packageName) {
        return 0;
    }

    @Override
    public long getApplicationRamUsage(String packageName) {
        return 0;
    }

    @Override
    public long getApplicationDataSize(String packageName) {
        return 0;
    }

    @Override
    public long getApplicationCacheSize(String packageName) {
        return 0;
    }

    @Override
    public boolean isApplicationRunning(String packageName) {
        return true;
    }

    @Override
    public String[] getAppInstalllist() {
        return null;
    }
    @Override
    public boolean startApp(String packageName,String clsName) {
        return true;
    }

    @Override
    public boolean stopApp(String packageName) {
        return true;
    }

    @Override
    public boolean wipeData(String packageName) {
        return true;
    }

    @Override
    public boolean unallowRunning(List list) {
        return true;
    }

    @Override
    public boolean allowDaemon(List list) {
        return true;
    }

    @Override
    public boolean forceClear(List list, int type) {
        return true;
    }

    @Override
    public boolean enableClear(List list, int type) {
        return true;
    }

    @Override
    public boolean forceUnIntsallApp(String packageName) {
         return true;
    }

    @Override
    public boolean enableInstallApp(String packageName) {
        return false;
    }

    @Override
    public boolean forceDaemon(List list) {
        return true;
    }

    @Override
    public void release() {
        deviceControlManager=null;
    }
}
