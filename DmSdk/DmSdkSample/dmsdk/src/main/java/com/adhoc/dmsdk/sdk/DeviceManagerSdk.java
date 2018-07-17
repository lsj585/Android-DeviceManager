package com.adhoc.dmsdk.sdk;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.IDeviceManager;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerUnsupportException;
import com.nd.adhoc.dmsdk.api.exception.ErrorCode;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_GetPackageList;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_IsRun;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_Run;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_Stop;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_WipeData;
import com.nd.adhoc.dmsdk.api.manager.hardware.IBluetoothManager;
import com.nd.adhoc.dmsdk.api.manager.hardware.ICameraManager;
import com.nd.adhoc.dmsdk.api.manager.hardware.IDeviceLockManager;
import com.nd.adhoc.dmsdk.api.manager.hardware.IMicrophoneManager;
import com.nd.adhoc.dmsdk.api.manager.hardware.IMobileDataManager;
import com.nd.adhoc.dmsdk.api.manager.hardware.ISdCardManager;
import com.nd.adhoc.dmsdk.api.manager.hardware.IUsbMamager;
import com.nd.adhoc.dmsdk.api.manager.hardware.IWifiManager;
import com.nd.adhoc.dmsdk.api.manager.key.IPhysicalKeyManager_Menu;
import com.nd.adhoc.dmsdk.api.manager.license.ILicenseManager_Active;
import com.nd.adhoc.dmsdk.api.manager.license.ILicenseManager_DeActive;
import com.nd.adhoc.dmsdk.api.manager.pac.IPackageManager_Install;
import com.nd.adhoc.dmsdk.api.manager.pac.IPackageManager_Uninstall;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowInstall;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowRun;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowStop;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowUnInstall;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowWipeData;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowInstall;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowRun;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowStop;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowUninstall;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowWipeData;
import com.nd.adhoc.dmsdk.api.manager.system.ISystemManager_ApplicationList;
import com.nd.adhoc.dmsdk.api.manager.system.ISystemManager_Backup;
import com.nd.adhoc.dmsdk.api.manager.system.ISystemManager_Brightness;
import com.nd.adhoc.dmsdk.api.manager.system.ISystemManager_RestoreProduct;
import com.nd.adhoc.dmsdk.api.manager.system.ISystemManager_Volumn;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;
import com.nd.adhoc.dmsdk.revicer.AdminReciver;
import com.nd.sdp.android.serviceloader.AnnotationServiceLoader;
import com.nd.sdp.android.serviceloader.ServiceLoader;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SDK入口类 管理对象池
 */
public class DeviceManagerSdk {


    private static DeviceManagerSdk instance;

    private Map<String, RealObject> maps = new ConcurrentHashMap();

    private DevicePolicyManager mDevicePolicyManager;

    private ComponentName mComponetName;

    private DeviceManagerSdk() {
    }


    public synchronized static DeviceManagerSdk getInstance() {

        if (instance == null) {
            instance = new DeviceManagerSdk();
        }
        return instance;
    }


    public void registerSDK(@NonNull Context context) {
        initManager();
        mDevicePolicyManager = (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        mComponetName = new ComponentName(context, AdminReciver.class);
        //sdk初始化时，注入DevicePolicyManager和componentName
        DeviceManagerContainer.getInstance().init(mDevicePolicyManager, mComponetName);
    }


    private void initManager() {
        //测试安全测试管理器
        /**
         * security
         */
        maps.put(DmSdkConstants.MANAGER_SECURITY_ALLOWWIPEDATA, new RealObject(ISecurityManager_AllowWipeData.class));
        maps.put(DmSdkConstants.MANAGER_SECURITY_ALLOWUNINSTALL, new RealObject(ISecurityManager_AllowUnInstall.class));
        maps.put(DmSdkConstants.MANAGER_SECURITY_ALLOWINSTALL, new RealObject(ISecurityManager_AllowInstall.class));
        maps.put(DmSdkConstants.MANAGER_SECURITY_ALLOWRUN, new RealObject(ISecurityManager_AllowRun.class));
        maps.put(DmSdkConstants.MANAGER_SECURITY_ALLOWSTOP, new RealObject(ISecurityManager_AllowStop.class));
        maps.put(DmSdkConstants.MANAGER_SECURITY_DISALLOWWIPEDATA, new RealObject(ISecurityManager_DisallowWipeData.class));
        maps.put(DmSdkConstants.MANAGER_SECURITY_DISALLOWUNINSTALL, new RealObject(ISecurityManager_DisallowUninstall.class));
        maps.put(DmSdkConstants.MANAGER_SECURITY_DISALLOWINSTALL, new RealObject(ISecurityManager_DisallowInstall.class));
        maps.put(DmSdkConstants.MANAGER_SECURITY_DISALLOWRUN, new RealObject(ISecurityManager_DisallowRun.class));
        maps.put(DmSdkConstants.MANAGER_SECURITY_DISALLOWSTOP, new RealObject(ISecurityManager_DisallowStop.class));
//        maps.put(DeviceManagerContainer.MANAGER_SECURITY_DISALLOWSTOP,new RealObject(ISystemManager_ApplicationList.class));
        /**
         * system
         */
        maps.put(DmSdkConstants.MANAGER_SYSTEM_GETAPPLICATIONLIST, new RealObject(ISystemManager_ApplicationList.class));
        maps.put(DmSdkConstants.MANAGER_SYSTEM_BACKUP, new RealObject(ISystemManager_Backup.class));
        maps.put(DmSdkConstants.MANAGER_SYSTEM_BRIGHTNESS, new RealObject(ISystemManager_Brightness.class));
        maps.put(DmSdkConstants.MANAGER_SYSTEM_RESTOREPRODUCT, new RealObject(ISystemManager_RestoreProduct.class));
        maps.put(DmSdkConstants.MANAGER_SYSTEM_VOLUMN, new RealObject(ISystemManager_Volumn.class));
        /**
         * PAC
         */
        maps.put(DmSdkConstants.MANAGER_PACKAGE_INSTALL, new RealObject(IPackageManager_Install.class));
        maps.put(DmSdkConstants.MANAGER_PACKAGE_UNINSTALL, new RealObject(IPackageManager_Uninstall.class));
        /**
         *APP
         */
        maps.put(DmSdkConstants.MANAGER_APPLICATION_GETPACKAGELIST, new RealObject(IApplicationManager_GetPackageList.class));
        maps.put(DmSdkConstants.MANAGER_APPLICATION_WIPEDATA, new RealObject(IApplicationManager_WipeData.class));
        maps.put(DmSdkConstants.MANAGER_APPLICATION_RUN, new RealObject(IApplicationManager_Run.class));
        maps.put(DmSdkConstants.MANAGER_APPLICATION_STOP, new RealObject(IApplicationManager_Stop.class));
        maps.put(DmSdkConstants.MANAGER_APPLICATION_ISRUNNING, new RealObject(IApplicationManager_IsRun.class));
        /**
         * LICENSE
         */
        maps.put(DmSdkConstants.MANAGER_LICENSE_ACTIVE, new RealObject(ILicenseManager_Active.class));
        maps.put(DmSdkConstants.MANAGER_LICENSE_DEACTIVE, new RealObject(ILicenseManager_DeActive.class));
        /**
         * hardware
         */
        maps.put(DmSdkConstants.MANAGER_HARDWARE_BLUETOOTH, new RealObject(IBluetoothManager.class));
        maps.put(DmSdkConstants.MANAGER_HARDWARE_CAMERA, new RealObject(ICameraManager.class));
        maps.put(DmSdkConstants.MANAGER_HARDWARE_USB, new RealObject(IUsbMamager.class));
        maps.put(DmSdkConstants.MANAGER_HARDWARE_SDCARD, new RealObject(ISdCardManager.class));
        maps.put(DmSdkConstants.MANAGER_HARDWARE_WIFI, new RealObject(IWifiManager.class));
        maps.put(DmSdkConstants.MANAGER_HARDWARE_LOCK, new RealObject(IDeviceLockManager.class));
        maps.put(DmSdkConstants.MANAGER_HARDWARE_MOBILEDATA, new RealObject(IMobileDataManager.class));
        maps.put(DmSdkConstants.MANAGER_HARDWARE_MICROPHONE, new RealObject(IMicrophoneManager.class));
        /**
         * key
         */
        maps.put(DmSdkConstants.MANAGER_KEY_MENU, new RealObject(IPhysicalKeyManager_Menu.class));
    }

    /**
     * 从对象池中获取管理器
     *
     * @param manager
     * @return
     */
    public IDeviceManager getManager(String manager) throws DeviceManagerUnsupportException {
        IDeviceManager dManager = null;
        RealObject<IDeviceManager> realObject = maps.get(manager);
        //TODO ZYB 通过注解标识找到对应的manager类，以保证多个provider产品下的api对应的实现类被调起
        Class<IDeviceManager> instance = realObject.getDeviceManager();
        //TODO 该处要通过注解返回具体对象实例
        ServiceLoader serviceLoader = AnnotationServiceLoader.load(instance);
        Iterator<IDeviceManager> iterator = serviceLoader.iterator();
        if (iterator.hasNext()) {
            dManager = iterator.next();
        }
        if (dManager == null) {
            throw new DeviceManagerUnsupportException(ErrorCode.DEFAULT_OPERATION_ERROR);
        }
        return dManager;
    }

    /**
     * 销毁管理器实例
     *
     * @param
     */
    public void destoryManager(Context context, RealObject<IDeviceManager> realObject, IDeviceManager manager) {
        if (manager != null) {
            manager.release(context);
        }
        maps.remove(realObject);
    }

    /**
     * 释放
     */
    public void release() {
    }

    public boolean isResgisterSDK() {
        if (maps.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 内部类创建一个引用对象
     */
    class RealObject<T> {

        private Class dManager;

        public RealObject(Class<IDeviceManager> manager) {
            this.dManager = manager;
        }

        public Class<IDeviceManager> getDeviceManager() {
            return dManager;
        }
    }

    /**
     * 激活license 设备管理器
     *
     * @param context
     */
    public void registerLicense(@NonNull Context context) {

        ILicenseManager_Active licenseManagerActive = null;
        try {
            licenseManagerActive = (ILicenseManager_Active) getManager(DmSdkConstants.MANAGER_LICENSE_ACTIVE);
        } catch (DeviceManagerUnsupportException | UnsupportedOperationException e) {
            e.printStackTrace();
        }
        if (licenseManagerActive == null) {
            return;
        }
        try {
            licenseManagerActive.active(context);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }

    }

    /**
     * 取消激活设备管理器
     */
    public void unRegisterLicense() {

        ILicenseManager_DeActive licenseManagerActive = null;
        try {
            licenseManagerActive = (ILicenseManager_DeActive) getManager(DmSdkConstants.MANAGER_SECURITY_ALLOWINSTALL);
        } catch (DeviceManagerUnsupportException | UnsupportedOperationException e) {
            e.printStackTrace();
        }
        if (licenseManagerActive == null) {
            return;
        }
        try {
            licenseManagerActive.deActive();
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();
        }
    }
}
