package com.nd.adhoc.dmsdk.demo.model.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Log;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.DeviceManagerContainer;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.api.exception.DeviceManagerUnsupportException;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_GetPackageList;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_IsRun;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_Run;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_Stop;
import com.nd.adhoc.dmsdk.api.manager.app.IApplicationManager_WipeData;
import com.nd.adhoc.dmsdk.api.manager.pac.IPackageManager_Uninstall;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowInstall;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowRun;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowUnInstall;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_AllowWipeData;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowRun;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowStop;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowUninstall;
import com.nd.adhoc.dmsdk.api.manager.security.ISecurityManager_DisallowWipeData;
import com.nd.adhoc.dmsdk.demo.bean.ApplicationInfoBean;
import com.nd.adhoc.dmsdk.demo.model.BaseModel;
import com.nd.adhoc.dmsdk.demo.model.IAppManagerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取文件列表信息
 */
public class AppListManagerModel extends BaseModel<ApplicationInfoBean> implements IAppManagerModel {

    private List<ApplicationInfoBean> mApplicationInfoBeans;


    public AppListManagerModel(Context context) {
        super(context);
    }

    @Override
    public List<ApplicationInfoBean> getList() {

        if (mApplicationInfoBeans == null) {

            mApplicationInfoBeans = new ArrayList<ApplicationInfoBean>();
        }

        mApplicationInfoBeans.clear();
        return createList();
    }

    @Override
    public void update(ApplicationInfoBean applicationInfoBean, int position) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(position);
        }
    }

    @Override
    public void delete(ApplicationInfoBean fileInfoBean) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            mApplicationInfoBeans.remove(fileInfoBean);
        }
    }

    @Override
    public void delete(int position) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            mApplicationInfoBeans.remove(position);
        }
    }

    @Override
    public ApplicationInfoBean findById(long id) {
        return null;
    }

    @Override
    public void update(int position) {

    }

    @Override
    public void release() {
        mApplicationInfoBeans = null;
    }

    /**
     * 获取应用列表
     *
     * @return
     */
    private List<ApplicationInfoBean> createList() {
        Log.i(this.getClass().getName(), String.format("showList:%d", mApplicationInfoBeans.size()));
        PackageManager pm = this.context.getPackageManager();
        IApplicationManager_GetPackageList getPackageList = null;
        try {
            getPackageList = (IApplicationManager_GetPackageList) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_APPLICATION_GETPACKAGELIST);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
        }
        if (getPackageList != null) {
            List packageNameList = null;
            try {
                packageNameList = getPackageList.getApplicationPackageList(context);

                List<PackageInfo> packageList = new ArrayList<PackageInfo>();
                try {
                    if (packageNameList != null && packageNameList.size() > 0) {
                        productPackageList(packageNameList, packageList, pm);
                    }
                    if (packageList.size() > 0) {
                        productNoSystemPakcgaes(packageList, pm);
                    }
                    packageList.clear();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.i(this.getClass().getName(), String.format("showList 2 :%d", mApplicationInfoBeans.size()));


            } catch (DeviceManagerSecurityException e) {
                e.printStackTrace();
            }

        }


        return mApplicationInfoBeans;
    }

    /**
     * 生成一组非系统应用的APP
     *
     * @param packageList
     * @param pm
     */
    private void productNoSystemPakcgaes(List<PackageInfo> packageList, PackageManager pm) {

        for (int packIndex = 0; packIndex < packageList.size(); packIndex++) {
            PackageInfo packageInfo = packageList.get(packIndex);
            ApplicationInfoBean info = new ApplicationInfoBean();
            info.setName(packageInfo.applicationInfo.loadLabel(pm).toString());
            mApplicationInfoBeans.add(info);
            Log.i(this.getClass().getName(), String.format("packageNameList[i]:%s", packageInfo.packageName));
            info.setPackageName(packageInfo.packageName);
            productAppInfoLauncherName(pm, packageInfo, info);
//            info.setRamUsage(manager.getApplicationRamUsage(packageInfo.packageName));
//            info.setApplicationCacheSizeUsage(manager.getApplicationCacheSize(packageInfo.packageName));
//            info.setCpuUsage(manager.getApplicationCpuUsage(packageInfo.packageName));
//            info.setApplicationDataSizeUsage(manager.getApplicationDataSize(packageInfo.packageName));
            info.setAllowClearData(true);
            info.setAllowUninstall(true);
            info.setAllowRunning(true);
            info.setAllowStopApp(true);
        }
    }
    /**
     * 生成Package所对应的属性值
     *
     * @param pm
     * @param packageInfo 某个应用的包；
     * @param info        目标应用的参数类
     */
    private void productAppInfoLauncherName(PackageManager pm, PackageInfo packageInfo, ApplicationInfoBean info) {

        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveinfoList = pm.queryIntentActivities(resolveIntent, 0);
        String laucnhActivity = null;
        if (resolveinfoList != null && resolveinfoList.size() > 0) {

            OUTER:
            for (int index = 0; index < resolveinfoList.size(); index++) {
                ResolveInfo resolveInfo = resolveinfoList.get(index);
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                if (resolveInfo.activityInfo.applicationInfo.packageName.equalsIgnoreCase(packageInfo.packageName)) {
                    laucnhActivity = activityInfo.name;
                    break OUTER;
                }
            }

        }
        if (laucnhActivity != null) {
            Log.i(this.getClass().getName(), String.format("activityInfo[i]:%s", laucnhActivity));
            info.setLauncherName(laucnhActivity);
        }
    }

    private void productPackageList(List packageNameList, List<PackageInfo> packageInfos, PackageManager pm) {

        for (int i = 0; i < packageNameList.size(); i++) {
            PackageInfo packageInfo = null;
            try {
                String packageName = (String) packageNameList.get(i);
                if (!TextUtils.isEmpty(packageName)) {
                    packageInfo = pm.getPackageInfo(packageName, 0);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (packageInfo != null) {
                //判断是否是系统应用 如果是系统应用则不处理
                if ((packageInfo.applicationInfo.flags & android.content.pm.ApplicationInfo.FLAG_SYSTEM) != 0) {

                } else {
                    packageInfos.add(packageInfo);
                }
            }
        }

    }


    @Override
    public boolean startApp(int position) {
        ApplicationInfoBean bean = mApplicationInfoBeans.get(position);
        if (bean != null && bean.getPackageName() != null && bean.getLauncherName() != null) {
            IApplicationManager_Run applicationManagerRun = null;
            try {
                applicationManagerRun = (IApplicationManager_Run) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_APPLICATION_RUN);
            } catch (DeviceManagerUnsupportException e) {
                e.printStackTrace();
            }
            try {
                applicationManagerRun.startApp(context, bean.getPackageName(), bean.getLauncherName());
                return true;
            } catch (DeviceManagerSecurityException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }

    }

    @Override
    public boolean stopApp(int position) {
        ApplicationInfoBean bean = mApplicationInfoBeans.get(position);
        if (bean != null && bean.getPackageName() != null) {
            if (bean.isAllowStopApp()) {
                IApplicationManager_Stop applicationManagerStop = null;
                try {
                    applicationManagerStop = (IApplicationManager_Stop) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_APPLICATION_STOP);
                } catch (DeviceManagerUnsupportException e) {
                    e.printStackTrace();
                }
                try {
                    applicationManagerStop.stopApp(context, bean.getPackageName());
                    return true;
                } catch (DeviceManagerSecurityException e) {
                    e.printStackTrace();
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean wipeData(int position) {
        ApplicationInfoBean bean = mApplicationInfoBeans.get(position);
        if (bean != null && bean.getPackageName() != null) {
            if (bean.isAllowClearData()) {
                IApplicationManager_WipeData applicationManagerWipeData = null;
                try {
                    applicationManagerWipeData = (IApplicationManager_WipeData) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_APPLICATION_WIPEDATA);
                } catch (DeviceManagerUnsupportException e) {
                    e.printStackTrace();
                }
                try {
                    applicationManagerWipeData.clearData(context, bean.getPackageName());
                    return true;
                } catch (DeviceManagerSecurityException e) {
                    e.printStackTrace();

                } catch (DeviceManagerUnsupportException e) {
                    e.printStackTrace();
                }
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void update(int position, boolean isSuccess) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(position);
            //执行成功或失败
            if (isSuccess) {
                update(bean, position);
            }
        }
    }

    @Override
    public void updateWipeStatus(int position) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(position);
            //执行成功或失败
            bean.setRamUsage(0);
            bean.setApplicationDataSizeUsage(0);
            bean.setApplicationCacheSizeUsage(0);
            update(bean, position);
        }
    }

    @Override
    public boolean uninstallApp(int position) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(position);
            //判断是否允许卸载
            if (bean.isAllowUninstall()) {
                try {

                    IPackageManager_Uninstall uninstall = (IPackageManager_Uninstall) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_PACKAGE_UNINSTALL);
                    try {
                        uninstall.uninstall(context, bean.getPackageName());
                        return true;
                    } catch (DeviceManagerSecurityException e) {
                        e.printStackTrace();
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean unallowRunning(int position) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(position);
            List list = null;
            try {
                list = new ArrayList();
                list.add(bean.getPackageName());
                try {

                    ISecurityManager_DisallowRun securityManagerDisallowRun = (ISecurityManager_DisallowRun) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_SECURITY_DISALLOWRUN);
                    try {
                        List packages = new ArrayList();
                        packages.add(bean.getPackageName());
                        securityManagerDisallowRun.addPackageToRunList(context, packages);
                        return true;
                    } catch (DeviceManagerSecurityException e) {
                        e.printStackTrace();
                        return false;
                    }
                } catch (Exception e) {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (list != null) {
                    list = null;
                }
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean allowDaemon(int position) {

        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(position);
            List list = null;


            ISecurityManager_DisallowStop securityManagerDisalloStop = null;
            try {
                securityManagerDisalloStop = (ISecurityManager_DisallowStop) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_SECURITY_DISALLOWSTOP);
            } catch (DeviceManagerUnsupportException e) {
                e.printStackTrace();
            }
            if (securityManagerDisalloStop != null) {
                try {
                    list = new ArrayList();
                    list.add(bean.getPackageName());
                    List packages = new ArrayList();
                    packages.add(bean.getPackageName());
                    securityManagerDisalloStop.addPackageToStopList(context, packages);
                    return true;
                } catch (DeviceManagerSecurityException e) {
                    e.printStackTrace();
                    return false;
                }
            } else

            {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean forceClearData(int viewPosition) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(viewPosition);
            List list = null;
            ISecurityManager_DisallowWipeData wipeData = null;
            try {
                wipeData = (ISecurityManager_DisallowWipeData) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_SECURITY_DISALLOWWIPEDATA);
            } catch (DeviceManagerUnsupportException e) {
                e.printStackTrace();
            }
            if (wipeData != null) {
                try {
                    list = new ArrayList();
                    list.add(bean.getPackageName());
                    wipeData.addPackageToClearCacheList(context, list);
                } catch (DeviceManagerSecurityException e) {
                    e.printStackTrace();
                    if (list != null) {
                        list = null;
                    }
                    return false;
                }
            }
        } else {
            return false;
        }
        return false;
    }

    @Override
    public void updateToClearData(int position, boolean isClearData) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(position);
            //执行成功或失败
            bean.setAllowClearData(isClearData);
            update(bean, position);
        }
    }

    @Override
    public void updateToRunning(int position, boolean isRunning) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(position);
            //执行成功或失败
            bean.setAllowRunning(isRunning);
            update(bean, position);
        }
    }

    @Override
    public void updateToUninstall(int position, boolean isUninstall) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(position);
            //执行成功或失败
            bean.setAllowUninstall(isUninstall);
            update(bean, position);
        }
    }

    @Override
    public void updateToStopApp(int position, boolean isStop) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(position);
            //执行成功或失败
            bean.setAllowStopApp(isStop);
            update(bean, position);
        }
    }

    @Override
    public boolean forceUnInstall(int viewPosition) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(viewPosition);
            ISecurityManager_DisallowUninstall disallowUninstall = null;
            try {
                disallowUninstall = (ISecurityManager_DisallowUninstall) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_SECURITY_DISALLOWUNINSTALL);
            } catch (DeviceManagerUnsupportException e) {
                e.printStackTrace();
            }
            if (disallowUninstall != null) {
                try {
                    disallowUninstall.addPackageToUninstallList(context, bean.getPackageName());
                    return true;
                } catch (DeviceManagerSecurityException e) {
                    e.printStackTrace();
                    return false;
                } catch (DeviceManagerUnsupportException e) {
                    e.printStackTrace();
                }
            }
        } else {
            return false;
        }
        return false;
    }

    @Override
    public boolean allowRunApp(int viewPosition) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(viewPosition);
            List list = null;
            ISecurityManager_AllowRun allowInstall = null;
            try {
                allowInstall = (ISecurityManager_AllowRun) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_SECURITY_ALLOWRUN);
            } catch (DeviceManagerUnsupportException e) {
                e.printStackTrace();
            }
            if (allowInstall != null) {
                try {
                    list = new ArrayList();
                    list.add(bean.getPackageName());
                    bean.setAllowRunning(true);
                    allowInstall.removePackageToRunList(context, list);
                } catch (DeviceManagerSecurityException e) {
                    e.printStackTrace();
                    if (list != null) {
                        list = null;
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }

    @Override
    public boolean allowClearData(int viewPosition) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(viewPosition);
            List list = null;
            ISecurityManager_AllowWipeData allowWipeData = null;
            try {
                allowWipeData = (ISecurityManager_AllowWipeData) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_SECURITY_ALLOWWIPEDATA);
            } catch (DeviceManagerUnsupportException e) {
                e.printStackTrace();
            }
            if (allowWipeData != null) {
                try {
                    list = new ArrayList();
                    list.add(bean.getPackageName());
                    allowWipeData.removePackageToClearCacheList(context, list);
                } catch (DeviceManagerSecurityException e) {
                    e.printStackTrace();
                    if (list != null) {
                        list = null;
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }

    @Override
    public boolean allowUninstall(int viewPosition) {
        if (mApplicationInfoBeans != null && mApplicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = mApplicationInfoBeans.get(viewPosition);
            ISecurityManager_AllowUnInstall allowUnInstall = null;
            try {
                allowUnInstall = (ISecurityManager_AllowUnInstall) DeviceManagerSdk.getInstance().getManager(DeviceManagerContainer.MANAGER_SECURITY_ALLOWUNINSTALL);
            } catch (DeviceManagerUnsupportException e) {
                e.printStackTrace();
            }
            if (allowUnInstall != null) {
                try {
                    allowUnInstall.removePackageToUninstallList(context, bean.getPackageName());
                } catch (DeviceManagerSecurityException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } else {
            return false;
        }
        return false;
    }
}
