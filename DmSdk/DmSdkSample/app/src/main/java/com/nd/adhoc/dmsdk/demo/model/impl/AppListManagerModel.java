package com.nd.adhoc.dmsdk.demo.model.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import com.nd.adhoc.dmsdk.api.knox.manager.DeviceSdkApiManager;
import com.nd.adhoc.dmsdk.demo.bean.ApplicationInfoBean;
import com.nd.adhoc.dmsdk.demo.model.BaseModel;
import com.nd.adhoc.dmsdk.demo.model.IAppManagerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取文件列表信息
 */
public class AppListManagerModel extends BaseModel<ApplicationInfoBean> implements IAppManagerModel {

    private List<ApplicationInfoBean> applicationInfoBeans;

    private DeviceSdkApiManager manager;


    public AppListManagerModel(Context context) {
        super(context);
        manager = new DeviceSdkApiManager(context);
    }

    @Override
    public List<ApplicationInfoBean> getList() {

        if (applicationInfoBeans == null) {

            applicationInfoBeans = new ArrayList<ApplicationInfoBean>();
        }

        applicationInfoBeans.clear();
        return createList();
    }

    @Override
    public void update(ApplicationInfoBean applicationInfoBean, int position) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(position);
            bean.setRunning(applicationInfoBean.isRunning());
        }
    }

    @Override
    public void delete(ApplicationInfoBean fileInfoBean) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            applicationInfoBeans.remove(fileInfoBean);
        }
    }

    @Override
    public void delete(int position) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            applicationInfoBeans.remove(position);
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
        manager.release();
        applicationInfoBeans = null;
    }

    /**
     * 获取应用列表
     *
     * @return
     */
    private List<ApplicationInfoBean> createList() {
        Log.i(this.getClass().getName(), String.format("showList:%d", applicationInfoBeans.size()));
        PackageManager pm = this.context.getPackageManager();
        String[] packageNameList = manager.getAppInstalllist();
        List<PackageInfo> packageList = new ArrayList<PackageInfo>();
        try {
            if (packageNameList != null && packageNameList.length > 0) {
                productPackageList(packageNameList,packageList,pm);
            }
            if (packageList.size() > 0) {
                productNoSystemPakcgaes(packageList,pm);
            }
            packageList.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(this.getClass().getName(), String.format("showList 2 :%d", applicationInfoBeans.size()));

        return applicationInfoBeans;
    }

    /**
     * 生成一组非系统应用的APP
     * @param packageList
     * @param pm
     */
    private void productNoSystemPakcgaes(List<PackageInfo> packageList,PackageManager pm) {

        for (int packIndex = 0; packIndex < packageList.size(); packIndex++) {
            PackageInfo packageInfo = packageList.get(packIndex);
            ApplicationInfoBean info = new ApplicationInfoBean();
            info.setName(packageInfo.applicationInfo.loadLabel(pm).toString());
            applicationInfoBeans.add(info);
            Log.i(this.getClass().getName(), String.format("packageNameList[i]:%s", packageInfo.packageName));
            info.setPackageName(packageInfo.packageName);
            if (manager.isApplicationRunning(packageInfo.packageName)) {
                info.setRunning(true);
            }
            productAppInfoLauncherName(pm,packageInfo,info);
            info.setRamUsage(manager.getApplicationRamUsage(packageInfo.packageName));
            info.setApplicationCacheSizeUsage(manager.getApplicationCacheSize(packageInfo.packageName));
            info.setCpuUsage(manager.getApplicationCpuUsage(packageInfo.packageName));
            info.setApplicationDataSizeUsage(manager.getApplicationDataSize(packageInfo.packageName));
            info.setAllowClearData(true);
            info.setAllowUninstall(true);
            info.setAllowRunning(true);
            info.setAllowStopApp(true);
        }
    }
    /**
     * 生成Package所对应的属性值
     * @param pm
     * @param packageInfo 某个应用的包；
     * @param info  目标应用的参数类
     */
    private void productAppInfoLauncherName(PackageManager pm,PackageInfo packageInfo,ApplicationInfoBean info) {

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
                Log.i(this.getClass().getName(), String.format("activityInfo[i]:%s", activityInfo.packageName));
                Log.i(this.getClass().getName(), String.format("packageInfo[i]:%s", packageInfo.packageName));
                Log.i(this.getClass().getName(), String.format("resolveInfo[i]:%s", resolveInfo.resolvePackageName));
                if (resolveInfo.activityInfo.applicationInfo.packageName.equalsIgnoreCase(packageInfo.packageName)) {
                    laucnhActivity = activityInfo.name;
                    break OUTER;
                }
            }

        }
        if (laucnhActivity != null) {
            info.setLauncherName(laucnhActivity);
        }
    }

    private void productPackageList(String []packageNameList ,List<PackageInfo> packageInfos,PackageManager pm) {

        for (int i = 0; i < packageNameList.length; i++) {
            PackageInfo packageInfo = null;
            try {
                packageInfo = pm.getPackageInfo(packageNameList[i], 0);
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
        ApplicationInfoBean bean = applicationInfoBeans.get(position);
        if (bean != null && bean.getPackageName() != null && bean.getLauncherName() != null) {
            //判断是否可以运行
            if(bean.isRunning()) {
                return manager.startApp(bean.getPackageName(), bean.getLauncherName());
            }else{
                return false;
            }
        } else {
            return false;
        }

    }

    @Override
    public boolean stopApp(int position) {
        ApplicationInfoBean bean = applicationInfoBeans.get(position);
        if (bean != null && bean.getPackageName() != null) {
            if(bean.isAllowStopApp()) {
                return manager.stopApp(bean.getPackageName());
            }else{
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean wipeData(int position) {
        ApplicationInfoBean bean = applicationInfoBeans.get(position);
        if (bean != null && bean.getPackageName() != null) {
            if(bean.isAllowClearData()) {
                return manager.wipeData(bean.getPackageName());
            }else{
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void update(int position, boolean isSuccess) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(position);
            //执行成功或失败
            if (isSuccess) {
                bean.setRunning(bean.isRunning() == false ? true : false);
                update(bean, position);
            }
        }
    }

    @Override
    public void updateWipeStatus(int position) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(position);
            //执行成功或失败
            bean.setRamUsage(0);
            bean.setApplicationDataSizeUsage(0);
            bean.setApplicationCacheSizeUsage(0);
            update(bean, position);
        }
    }

    @Override
    public boolean uninstallApp(int position) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(position);
            //判断是否允许卸载
            if(bean.isAllowUninstall()) {
                try {
                    return manager.uninstallApp(bean.getPackageName());
                } catch (Exception e) {
                    return false;
                }
            }else{
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean unallowRunning(int position) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(position);
            List list=null;
            try {
                list=new ArrayList();
                list.add(bean.getPackageName());
                return manager.addToUnRunningAppList(list);
            } catch (Exception e) {
                e.printStackTrace();
                if(list != null) {
                    list=null;
                }
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean allowDaemon(int position) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(position);
            List list=null;
            try {
                list=new ArrayList();
                list.add(bean.getPackageName());
                return manager.allowDaemon(list,false);
            } catch (Exception e) {
                e.printStackTrace();
                //失败时，如果list有值，则手动至为Null;
                if(list != null) {
                    list=null;
                }
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean forceClearData(int viewPosition) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(viewPosition);
            List list=null;
            try {
                list=new ArrayList();
                list.add(bean.getPackageName());
                return manager.clearDataFromApp(list,0,false);
            } catch (Exception e) {
                e.printStackTrace();
                if(list != null) {
                    list=null;
                }
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public void updateToClearData(int position, boolean isClearData) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(position);
            //执行成功或失败
            bean.setAllowClearData(isClearData);
            update(bean, position);
        }
    }

    @Override
    public void updateToRunning(int position, boolean isRunning) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(position);
            //执行成功或失败
            bean.setAllowRunning(isRunning);
            update(bean, position);
        }
    }

    @Override
    public void updateToUninstall(int position, boolean isUninstall) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(position);
            //执行成功或失败
            bean.setAllowUninstall(isUninstall);
            update(bean, position);
        }
    }

    @Override
    public void updateToStopApp(int position, boolean isStop) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(position);
            //执行成功或失败
            bean.setAllowStopApp(isStop);
            update(bean, position);
        }
    }

    @Override
    public boolean forceUnInstall(int viewPosition) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(viewPosition);
            try {
                return manager.unInstallApp(bean.getPackageName(),false);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean allowRunApp(int viewPosition) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(viewPosition);
            try {
                return manager.unInstallApp(bean.getPackageName(),true);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean allowClearData(int viewPosition) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(viewPosition);
            List list=null;
            try {
                list=new ArrayList();
                list.add(bean.getPackageName());
                return manager.clearDataFromApp(list,0,true);
            } catch (Exception e) {
                e.printStackTrace();
                if(list != null){
                    list=null;
                }
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean allowUninstall(int viewPosition) {
        if (applicationInfoBeans != null && applicationInfoBeans.size() > 0) {
            ApplicationInfoBean bean = applicationInfoBeans.get(viewPosition);
            try {
                return manager.unInstallApp(bean.getPackageName(),true);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return false;
        }
    }
}
