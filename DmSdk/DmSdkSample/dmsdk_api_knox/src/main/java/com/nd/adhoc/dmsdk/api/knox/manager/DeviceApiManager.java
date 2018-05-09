package com.nd.adhoc.dmsdk.api.knox.manager;

import android.content.Context;

import com.nd.adhoc.dmsdk.api.knox.IKnoxDmApiManager;

import java.util.List;

/**
 * 主入口 SDK层调用该类
 */
public class DeviceApiManager extends IKnoxDmApiManager {

//    private KeyManager deviceKeyManager;

    private SystemManager deviceSystemManager;

    private LockManager deviceLockManager;

    private ControlManager controlManager;

    private LicenceManager licenceManager;

    private Context context;

    public DeviceApiManager(Context context){
        this.context=context;
    }

    private LockManager getDeviceLock() {
        if (deviceLockManager == null) {
            deviceLockManager = new LockManager();
            deviceLockManager.setContext(this.context);
        }
        return deviceLockManager;
    }
//
//    private KeyManager getDeviceKey() {
//        if (deviceKeyManager == null) {
//            deviceKeyManager = new KeyManager();
//        }
//        return deviceKeyManager;
//    }

    private SystemManager getDeviceSystem() {
        if (deviceSystemManager == null) {
            deviceSystemManager = new SystemManager();
            deviceSystemManager.setContext(context.getApplicationContext());
        }
        return deviceSystemManager;
    }

    private ControlManager getDeviceControl() {
        if (controlManager == null) {
            controlManager = new ControlManager();
            controlManager.setContext(context.getApplicationContext());
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
            licenceManager.release();
            licenceManager = null;
        }
        if (controlManager != null) {
            controlManager.release();
            controlManager = null;
        }
        if (deviceSystemManager != null) {
            deviceSystemManager.release();
            deviceSystemManager = null;
        }
        if (deviceLockManager != null) {
            deviceLockManager.release();
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
    public boolean wipeData(String packageName) {
        return getDeviceSystem().wipeData(packageName);
    }

    @Override
    public boolean addToUnRunningAppList(List list) {
        return getDeviceSystem().unallowRunning(list);
    }

    @Override
    public boolean allowDaemon(List list,boolean isStop) {
        if(isStop) {
            return getDeviceSystem().forceDaemon(list);
        }else{
            return getDeviceSystem().allowDaemon(list);
        }
    }

    @Override
    public boolean clearDataFromApp(List list, int type,boolean isClearData) {
        if(isClearData){
            return getDeviceSystem().enableClear(list, type);
        }else {
            return getDeviceSystem().forceClear(list, type);
        }
    }

    @Override
    public boolean unInstallApp(String packageName,boolean isUninstall) {
        if(!isUninstall) {
            return getDeviceSystem().forceUnIntsallApp(packageName);
        }else{
            return getDeviceSystem().enableInstallApp(packageName);
        }
    }

    @Override
    public boolean restorFactory() {
        return getDeviceControl().deviceWipeData();
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
            return getDeviceControl().openUsb();
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
            return getDeviceControl().closeBluetooth();
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


    public boolean uninstallApp(String packageName) {
        return getDeviceSystem().uninstallApp(packageName);
    }


//    public void remoteService() {
//        getDeviceSystem().remoteService();
//    }
//
//
//    public void remoteVolumnKey() {
//        getDeviceKey().remoteVolumnKey();
//    }
//
//
//    public void remoteHomeKey() {
//        getDeviceKey().remoteHomeKey();
//    }
//
//
//    public void remoteBackKey() {
//        getDeviceKey().remoteBackKey();
//    }
//
//
//    public void remoteBrightness() {
//        getDeviceKey().remoteBrightness();
//    }



    public long getApplicationCpuUsage(String packageName) {
        try {
            return getDeviceSystem().getApplicationCpuUsage(packageName);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    public long getApplicationRamUsage(String packageName) {
        try {
            return getDeviceSystem().getApplicationRamUsage(packageName);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    public long getApplicationDataSize(String packageName) {
        try {
            return getDeviceSystem().getApplicationDataSize(packageName);
        }catch (Exception e){
            e.printStackTrace();
            return  0;
        }
    }


    public long getApplicationCacheSize(String packageName) {
        try {
            return getDeviceSystem().getApplicationCacheSize(packageName);
        }catch (Exception e){
            return 0;
        }
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

    public boolean isOpenWifi() {
        return getDeviceControl().isOpenWifi();
    }

    public  boolean isLock(){
        return getDeviceLock().isLock();
    }

}
