package com.nd.adhoc.dmsdk.api.knox.manager;

import android.content.Context;

import com.nd.adhoc.dmsdk.api.knox.IKnoxDmApiManager;

/**
 * 主入口 SDK层调用该类
 */
public class DeviceApiManager extends IKnoxDmApiManager {

    private KeyManager deviceKeyManager;

    private SystemManager deviceSystemManager;

    private LockManager deviceLockManager;

    private ControlManager controlManager;

    private LicenceManager licenceManager;

    private Context context;

    public DeviceApiManager(Context context){
        this.context=context.getApplicationContext();
    }

    private LockManager getDeviceLock() {
        if (deviceLockManager == null) {
            deviceLockManager = new LockManager();
            deviceLockManager.setContext(this.context);
        }
        return deviceLockManager;
    }

    private KeyManager getDeviceKey() {
        if (deviceKeyManager == null) {
            deviceKeyManager = new KeyManager();
        }
        return deviceKeyManager;
    }

    private SystemManager getDeviceSystem() {
        if (deviceSystemManager == null) {
            deviceSystemManager = new SystemManager();
            deviceSystemManager.setContext(context);
        }
        return deviceSystemManager;
    }

    private ControlManager getDeviceControl() {
        if (controlManager == null) {
            controlManager = new ControlManager();
            controlManager.setContext(context);
        }
        return controlManager;
    }

    private LicenceManager getLicence() {
        if (licenceManager == null) {
            licenceManager = new LicenceManager();
            licenceManager.setContext(context);
        }
        return licenceManager;
    }

    /**
     * 释放
     */
    public void release() {
        if (licenceManager != null) {
            licenceManager = null;
        }
        if (controlManager != null) {
            controlManager = null;
        }
        if (deviceSystemManager != null) {
            deviceSystemManager = null;
        }

        if (deviceKeyManager != null) {
            deviceKeyManager = null;
        }

        if (deviceLockManager != null) {
            deviceLockManager = null;
        }

    }

    @Override
    public boolean startApp(String packageName,String launcherName) {
        return getDeviceSystem().startApp(packageName,launcherName);
    }

    @Override
    public boolean stopApp(String packageName) {
        return getDeviceSystem().stopApp(packageName);
    }

    @Override
    public boolean deviceLock() {
        if (getDeviceLock().isLock()) {
            return getDeviceLock().unlock();
        } else {
            return getDeviceLock().lock();
        }
    }

    @Override
    public void deivcePowerOff() {
        getDeviceSystem().powerOff();
    }

    @Override
    public boolean deviceCamera() {
        if (getDeviceControl().isOpenCamrea()) {
           return getDeviceControl().cloaseCamera();
        } else {
            return getDeviceControl().openCamera();
        }
    }

    @Override
    public boolean deviceUsb() {
        if (getDeviceControl().isOpenUsb()) {
            return getDeviceControl().closeUsb();
        } else {
            return getDeviceControl().closeUsb();
        }
    }

    @Override
    public boolean deviceMicrophone() {
        if (getDeviceControl().isOpenMicrophone()) {
            return getDeviceControl().closeMicrophone();
        } else {
            return getDeviceControl().openMicrophone();
        }
    }

    @Override
    public boolean deviceWifi() {
        if (getDeviceControl().isOpenWifi()) {
            return getDeviceControl().closeWifi();
        } else {
            return getDeviceControl().openWifi();
        }
    }

    @Override
    public boolean deviceLTE() {
        if (getDeviceControl().isOpenLTE()) {
            return getDeviceControl().closeLTE();
        } else {
            return  getDeviceControl().openLTE();
        }
    }

    @Override
    public boolean deviceMobileNetwork() {
        if (getDeviceControl().isOpenNetwork()) {
            return getDeviceControl().closeNetwork();
        } else {
            return getDeviceControl().openNetwork();
        }
    }

    @Override
    public void activeLicense() {
        getLicence().activieLicnece();
    }

    @Override
    public boolean deviceBluetooth() {

        if(getDeviceControl().isOpenBluetooth()){
            return  getDeviceControl().closeBluetooth();
        }else{
            return getDeviceControl().openBluetooth();
        }
    }

    @Override
    public boolean deviceSdCard() {
        if(getDeviceControl().isOpenSdCard()){
            return  getDeviceControl().closeSdCard();
        }else{
            return getDeviceControl().openSdCard();
        }
    }

    public void sleep() {
        getDeviceSystem().sleep();
    }


    public void wake() {
        getDeviceSystem().wake();
    }


    public void remoteDormancy() {
        getDeviceSystem().remoteDormancy();
    }


    public void remoteApp() {
        getDeviceSystem().remoteApp();
    }


    public void getRemoteAppList() {
        getDeviceSystem().getRemoteAppList();
    }


    public boolean installApp(String apkFile) {
       return getDeviceSystem().installApp(apkFile);
    }


    public void uninstallApp() {
        getDeviceSystem().uninstallApp();
    }


    public void remoteService() {
        getDeviceSystem().remoteService();
    }


    public void remoteVolumnKey() {
        getDeviceKey().remoteVolumnKey();
    }


    public void remoteHomeKey() {
        getDeviceKey().remoteHomeKey();
    }


    public void remoteBackKey() {
        getDeviceKey().remoteBackKey();
    }


    public void remoteBrightness() {
        getDeviceKey().remoteBrightness();
    }



    public long getApplicationCpuUsage(String packageName) {
        return getDeviceSystem().getApplicationCpuUsage(packageName);
    }


    public long getApplicationRamUsage(String packageName) {
        return getDeviceSystem().getApplicationRamUsage(packageName);
    }


    public long getApplicationDataSize(String packageName) {
        return getDeviceSystem().getApplicationDataSize(packageName);
    }


    public long getApplicationCacheSize(String packageName) {
        return getDeviceSystem().getApplicationCacheSize(packageName);
    }


    public boolean isApplicationRunning(String packageName) {
        return getDeviceSystem().isApplicationRunning(packageName);
    }


    public String[] getAppInstalllist() {
        return getDeviceSystem().getAppInstalllist();
    }

    public boolean isOpenCamrea() {
        return getDeviceControl().isOpenCamrea();
    }

    public boolean isOpenUsb() {
        return getDeviceControl().isOpenUsb();
    }

    public boolean isOpenBluetooth() {
        return getDeviceControl().isOpenBluetooth();
    }

    public boolean isOpenNetwork() {
        return getDeviceControl().isOpenNetwork();
    }

    public boolean isOpenMicrophone() {
        return getDeviceControl().isOpenMicrophone();
    }

    public boolean isOpenLTE() {
        return getDeviceControl().isOpenLTE();
    }

    public boolean isOpenWifi() {
        return getDeviceControl().isOpenWifi();
    }

    public  boolean isLock(){
        return getDeviceLock().isLock();
    }
}
