package com.nd.adhoc.dmsdk.api.knox.manager;

import android.app.enterprise.ApplicationPolicy;
import android.content.Context;

import com.nd.adhoc.dmsdk.api.IDeviceSystem;

class SystemManager extends BaseManager implements IDeviceSystem {
    private ApplicationPolicy applicationPolicy;
    @Override
    public void setContext(Context context) {
        super.setContext(context);
        applicationPolicy=deviceManager.getApplicationPolicy();
    }

    @Override
    public void powerOff() {

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
    public void uninstallApp() {

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
}
