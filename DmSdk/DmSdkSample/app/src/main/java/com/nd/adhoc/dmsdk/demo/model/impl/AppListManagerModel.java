package com.nd.adhoc.dmsdk.demo.model.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import com.nd.adhoc.dmsdk.api.knox.manager.DeviceApiManager;
import com.nd.adhoc.dmsdk.demo.bean.ApplicationInfoBean;
import com.nd.adhoc.dmsdk.demo.bean.FileInfoBean;
import com.nd.adhoc.dmsdk.demo.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取文件列表信息
 */
public class AppListManagerModel extends BaseModel<ApplicationInfoBean> {

    private List<ApplicationInfoBean> applicationInfoBeans;

    private DeviceApiManager manager;


    public AppListManagerModel(Context context) {
        super(context);
        manager = new DeviceApiManager(context);
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

    }

    @Override
    public void delete(ApplicationInfoBean fileInfoBean) {

    }

    @Override
    public ApplicationInfoBean findById(long id) {
        return null;
    }

    @Override
    public void update(int position) {
        ApplicationInfoBean bean = applicationInfoBeans.get(position);
        if (bean != null && bean.getLauncherName() != null) {
            Log.i(this.getClass().getName(), String.format("is name:%s",bean.getLauncherName()));
        } else {
            Log.i(this.getClass().getName(), String.format("is name:%s","error"));
        }
    }

    @Override
    public void release() {
        manager.release();
        applicationInfoBeans=null;
    }

    /**
     * 获取应用列表
     *
     * @return
     */
    private List<ApplicationInfoBean> createList() {
        Log.i(this.getClass().getName(),String.format("showList:%d", applicationInfoBeans.size()));

        PackageManager pm = this.context.getPackageManager();
        String[] packageNameList = manager.getAppInstalllist();
        if (packageNameList != null && packageNameList.length > 0) {
            for (int i = 0; i < packageNameList.length; i++) {
                PackageInfo packageInfo = null;
                try {
                    packageInfo = pm.getPackageInfo(packageNameList[i], 0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                if (packageInfo != null) {
                    ApplicationInfoBean info = new ApplicationInfoBean();
                    info.setName(packageInfo.applicationInfo.loadLabel(pm).toString());
                    Log.i(this.getClass().getName(),String.format("packageNameList[i]:%s",packageInfo.packageName));
                    info.setPackageName(packageInfo.packageName);

                    if(manager.isApplicationRunning(packageInfo.packageName)){
                        info.setRunning(true);
                    }
                    //判断是否是系统应用 如果是系统应用则不处理
                    if((packageInfo.applicationInfo.flags & android.content.pm.ApplicationInfo.FLAG_SYSTEM)!=0){

                    }else {
                        applicationInfoBeans.add(info);
                    }
                    // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
                    Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
                    resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
                    List<ResolveInfo> resolveinfoList = pm.queryIntentActivities(resolveIntent, 0);
                    String laucnhActivity=null;
                    if (resolveinfoList != null && resolveinfoList.size() > 0) {

                        OUTER: for(int index=0;index<resolveinfoList.size();index++){
                            ResolveInfo resolveInfo = resolveinfoList.get(index);
                            ActivityInfo activityInfo = resolveInfo.activityInfo;
                            Log.i(this.getClass().getName(),String.format("activityInfo[i]:%s",activityInfo.packageName));
                            Log.i(this.getClass().getName(),String.format("packageInfo[i]:%s",packageInfo.packageName));
                            Log.i(this.getClass().getName(),String.format("resolveInfo[i]:%s",resolveInfo.resolvePackageName));
                            if(resolveInfo.activityInfo.applicationInfo.packageName.equalsIgnoreCase(packageInfo.packageName)){
                                laucnhActivity=activityInfo.name;
                                break OUTER;
                            }
                        }

                    }
                    if(laucnhActivity !=null){
                        info.setLauncherName(laucnhActivity);
                    }
                }
            }
        }
        Log.i(this.getClass().getName(),String.format("showList 2 :%d", applicationInfoBeans.size()));

        return applicationInfoBeans;
    }
}
