package com.nd.adhoc.dmsdk;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.support.annotation.NonNull;

/**
 * 设备管理器工厂
 */
public class DeviceManagerContainer {



    private static DeviceManagerContainer mDeviceManagrFactory;



    private DevicePolicyManager mDevicePolicyManager;

    private ComponentName mComponentName;
    /**
     * 安全策略模式 允许应用被安装
     */
    public static final String MANAGER_SECURITY_ALLOWINSTALL="AllowInstall";
    /**
     * 安全策略模式 允许应用数据被清除
     */
    public static final String MANAGER_SECURITY_ALLOWWIPEDATA="AllowWipeData";
    /**
     * 安全策略模式 允许应用被卸载
     */
    public static final String MANAGER_SECURITY_ALLOWUNINSTALL = "AllowUnInstall";
    /**
     * 安全策略模式 允许应用被运行
     */
    public static final String MANAGER_SECURITY_ALLOWRUN="AllowRun";
    /**
     * 安全策略模式 允许应用被停止
     */
    public static final String MANAGER_SECURITY_ALLOWSTOP="AllowStop";
    /**
     * 安全策略模式 不允许应用被卸载
     */
    public static final String MANAGER_SECURITY_DISALLOWUNINSTALL = "DisAllowUninstall";
    /**
     * 安全策略模式 不允许应用被安装
     */
    public static final String MANAGER_SECURITY_DISALLOWINSTALL ="DisAllowInstall" ;
    /**
     * 安全策略模式 不允许应用运行
     */
    public static final String MANAGER_SECURITY_DISALLOWRUN ="DisAllowRun" ;
    /**
     * 安全策略模式 不允许应用被停止
     */
    public static final String MANAGER_SECURITY_DISALLOWSTOP ="DisAllowStop" ;
    /**
     *安全策略模式 不允许应用被清理数据
     */
    public static String MANAGER_SECURITY_DISALLOWWIPEDATA="DisallowWipeData";
    /**
     *
     */
    public static final String MANAGER_SYSTEM_GETAPPLICATIONLIST = "GetApplicationList";
    /**
     *
     */
    public static final String MANAGER_SYSTEM_BACKUP = "backup";
    /**
     *
     */
    public static final String MANAGER_SYSTEM_BRIGHTNESS = "brightness";
    /**
     *
     */
    public static final String MANAGER_SYSTEM_RESTOREPRODUCT ="restore_product" ;
    /**
     *
     */
    public static final String MANAGER_SYSTEM_VOLUMN = "volumn";
    /**
     *
     */
    public static final String MANAGER_PACKAGE_INSTALL = "package_install";
    /**
     *
     */
    public static final String MANAGER_PACKAGE_UNINSTALL = "package_uninstall";
    /**
     *
     */
    public static final String MANAGER_APPLICATION_GETPACKAGELIST = "";
    /**
     *
     */
    public static final String MANAGER_APPLICATION_WIPEDATA ="application_wipedata" ;
    /**
     *
     */
    public static final String MANAGER_APPLICATION_RUN = "application_run";
    /**
     *
     */
    public static final String MANAGER_APPLICATION_STOP = "application_stop";
    /**
     *
     */
    public static final String MANAGER_LICENSE_ACTIVE = "license_active";
    /**
     *
     */
    public static final String MANAGER_LICENSE_DEACTIVE = "license_deactive";
    /**
     *
     */
    public static final String MANAGER_HARDWARE_BLUETOOTH ="bluetooth" ;
    /**
     *
     */
    public static final String MANAGER_HARDWARE_CAMERA ="camera" ;
    /**
     *
     */
    public static final String MANAGER_HARDWARE_USB = "usb";
    /**
     *
     */
    public static final String MANAGER_HARDWARE_SDCARD = "sdcard";
    /**
     *
     */
    public static final String MANAGER_HARDWARE_WIFI = "wifi";
    /**
     *
     */
    public static final String MANAGER_HARDWARE_LOCK = "DeviceLock";
    /**
     *
     */
    public static final String MANAGER_HARDWARE_MOBILEDATA = "mobile";
    /**
     *
     */
    public static final String MANAGER_HARDWARE_MICROPHONE = "microphone";


    public  static DeviceManagerContainer getInstance() {
        //使用同步锁，避免在多个线程中使用，导致产生多个实体类。
        if (mDeviceManagrFactory == null) {
            synchronized (DeviceManagerContainer.class){
                if(mDeviceManagrFactory==null){
                    mDeviceManagrFactory = new DeviceManagerContainer();
                }
            }
        }
        return mDeviceManagrFactory;
    }





    public void init(@NonNull DevicePolicyManager devicePolicyManager, @NonNull ComponentName componentName) {
        this.mDevicePolicyManager=devicePolicyManager;
        this.mComponentName=componentName;
    }


    public void init(@NonNull DevicePolicyManager devicePolicyManager){

        this.mDevicePolicyManager=devicePolicyManager;
    }

    public DevicePolicyManager getDevicePolicyManager() {
        if(mDevicePolicyManager==null){
            return null;
        }
        return mDevicePolicyManager;
    }

    public ComponentName getComponentName() {
        if(mComponentName==null){
            return null;
        }
        return mComponentName;
    }

    public void release(){

        if(mDeviceManagrFactory==null){
            mDeviceManagrFactory=null;
        }
    }
}
