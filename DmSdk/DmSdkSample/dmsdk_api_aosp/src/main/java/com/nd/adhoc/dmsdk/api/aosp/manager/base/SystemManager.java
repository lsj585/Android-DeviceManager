//package com.nd.adhoc.dmsdk.api.aosp.manager.base;
//import android.content.Context;
//
//import com.huawei.android.app.admin.DeviceApplicationManager;
//import com.huawei.android.app.admin.DeviceControlManager;
//import com.huawei.android.app.admin.DevicePackageManager;
//import com.nd.adhoc.dmsdk.api.IDeviceSystem;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class SystemManager extends BaseManager implements IDeviceSystem {
//
//
//    private DeviceControlManager deviceControlManager;
//
//    private DevicePackageManager devicePackageManager;
//
//    private DeviceApplicationManager deviceApplicationManager;
//
//    private List<String> packageList;
//
//    @Override
//    public void setContext(Context context) {
//        super.setContext(context);
//        deviceControlManager=new DeviceControlManager();
//        devicePackageManager=new DevicePackageManager();
//        deviceApplicationManager=new DeviceApplicationManager();
//        packageList=new ArrayList<String>();
//    }
//
//    @Override
//    public void powerOff() {
//        deviceControlManager.shutdownDevice(getComponentName());
//    }
//
//    @Override
//    public void sleep() {
//
//    }
//
//    @Override
//    public void wake() {
//
//    }
//
//    @Override
//    public void remoteDormancy() {
//
//    }
//
//    @Override
//    public void remoteApp() {
//
//    }
//
//    @Override
//    public void getRemoteAppList() {
//
//    }
//
//    @Override
//    public void reboot() {
//        deviceControlManager.rebootDevice(getComponentName());
//    }
//
//    @Override
//    public boolean installApp(String apkFile) {
//        try{
//            devicePackageManager.installPackage(getComponentName(),apkFile);
//            return true;
//        }catch (SecurityException e){
//            e.printStackTrace();
//            return false;
//        }catch (IllegalArgumentException e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public boolean uninstallApp(String apkName) {
//        try{
//            devicePackageManager.uninstallPackage(getComponentName(),apkName,true);
//            return true;
//        }catch (SecurityException e){
//            e.printStackTrace();
//            return false;
//        }catch (IllegalArgumentException e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public void remoteService() {
//
//    }
//
//    @Override
//    public long getApplicationCpuUsage(String packageName) {
//        /**
//         * 没有提供API，只能用android自带的API，获取应用列表
//         */
//        return 0;
//    }
//
//    @Override
//    public long getApplicationRamUsage(String packageName) {
//        /**
//         * 没有提供API，只能用android自带的API，获取应用列表
//         */
//        return 0;
//    }
//
//    @Override
//    public long getApplicationDataSize(String packageName) {
//        /**
//         * 没有提供API，只能用android自带的API，获取应用列表
//         */
//        return 0;
//    }
//
//    @Override
//    public long getApplicationCacheSize(String packageName) {
//        /**
//         * 没有提供API，只能用android自带的API，获取应用列表
//         */
//        return 0;
//    }
//
//    @Override
//    public boolean isApplicationRunning(String packageName) {
//        /**
//         * 没有提供API,请使用系统自带的API
//         */
//        return true;
//    }
//
//    @Override
//    public String[] getAppInstalllist() {
//        /**
//         * 没有提供API，只能用android自带的API，获取应用列表
//         */
//        return null;
//    }
//    @Override
//    public boolean startApp(String packageName,String clsName) {
//        /**
//         * 没有提供API,请使用系统自带的API
//         */
//        return false;
//    }
//
//    @Override
//    public boolean stopApp(String packageName) {
//        try{
//            deviceApplicationManager.killApplicationProcess(getComponentName(),packageName);
//            return true;
//        }catch (SecurityException e){
//            e.printStackTrace();
//            return false;
//        }catch (IllegalArgumentException e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public boolean wipeData(String packageName) {
//        try{
//            devicePackageManager.clearPackageData(getComponentName(),packageName);
//            return true;
//        }catch (SecurityException e){
//            e.printStackTrace();
//            return false;
//        }catch (IllegalArgumentException e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public boolean unallowRunning(List list) {
//        try{
//            deviceApplicationManager.addDisallowedRunningApp(getComponentName(),list);
//            return true;
//        }catch (SecurityException e){
//            e.printStackTrace();
//            return false;
//        }catch (IllegalArgumentException e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public boolean allowDaemon(List list) {
//        try{
//            deviceApplicationManager.addPersistentApp(getComponentName(),list);
//            return true;
//        }catch (SecurityException e){
//            e.printStackTrace();
//            return false;
//        }catch (IllegalArgumentException e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public boolean forceClear(List list, int type) {
//        /**
//         * 阻止清除某应用数据，没有提供API
//         */
//        return true;
//    }
//
//    @Override
//    public boolean enableClear(List list, int type) {
//        /**
//         * 阻止清除某应用数据，没有提供API
//         */
//        return true;
//    }
//
//    @Override
//    public boolean forceUnIntsallApp(String packageName) {
//        if(packageList.size()>0 && !packageList.contains(packageName)){
//            packageList.add(packageName);
//        }
//        try{
//            devicePackageManager.addDisallowedUninstallPackages(getComponentName(),packageList);
//            return true;
//        }catch (SecurityException e){
//            e.printStackTrace();
//            return false;
//        }catch (IllegalArgumentException e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public boolean enableInstallApp(String packageName) {
//        if(packageList.size()>0 && !packageList.contains(packageName)){
//            packageList.add(packageName);
//        }
//        try{
//            devicePackageManager.removeDisallowedUninstallPackages(getComponentName(),packageList);
//            return true;
//        }catch (SecurityException e){
//            e.printStackTrace();
//            return false;
//        }catch (IllegalArgumentException e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public boolean forceDaemon(List list) {
//        try{
//            deviceApplicationManager.removePersistentApp(getComponentName(),list);
//            return true;
//        }catch (SecurityException e){
//            e.printStackTrace();
//            return false;
//        }catch (IllegalArgumentException e){
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    @Override
//    public void release() {
//        deviceControlManager=null;
//        devicePackageManager=null;
//        deviceApplicationManager=null;
//        packageList=null;
//    }
//}
