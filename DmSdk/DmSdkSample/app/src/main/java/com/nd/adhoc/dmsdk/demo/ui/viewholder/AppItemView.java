package com.nd.adhoc.dmsdk.demo.ui.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.Formatter;
import android.view.View;
import android.widget.TextView;

import com.adhoc.dmsdk.sdk.DeviceManagerSdk;
import com.nd.adhoc.dmsdk.exception.DeviceManagerSecurityException;
import com.nd.adhoc.dmsdk.exception.DeviceManagerUnsupportException;
import com.nd.adhoc.dmsdk.api.app.IApp_IsRun;
import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.bean.ApplicationInfoBean;
import com.nd.adhoc.dmsdk.filed.DmSdkConstants;

import java.util.List;

/**
 * @author richsjeson
 * 未安装的APP列表
 */

public class AppItemView{

    private TextView tvAppInfoName;
    private TextView tvPackageName;
    private TextView tvRunning;
    private TextView tvUsage;
    public AppItemView(View itemView) {
        tvAppInfoName =itemView.findViewById(R.id.tv_appname_app);
        tvPackageName = itemView.findViewById(R.id.tv_package_app);
        tvRunning = itemView.findViewById(R.id.tv_running_app);
        tvUsage = itemView.findViewById(R.id.tv_usage_app);
    }

    public void setView(@NonNull Context context,@NonNull  List<ApplicationInfoBean> list,int position) {

        if (list.size() == 0) {
            return;
        }

        if (list.get(position) == null) {
            return;
        }

        if (isRunning(context, list.get(position).getPackageName())) {
            tvRunning.setText(context.getResources().getString(R.string.runing));
        } else {
           tvRunning.setText(context.getResources().getString(R.string.stop));
        }

       tvUsage.setText(String.format(context.getResources().getString(R.string.app_usage),
                formatSize(context,list.get(position).getCpuUsage()),
                formatSize(context,list.get(position).getRamUsage()),
                formatSize(context,list.get(position).getApplicationDataSizeUsage()),
                formatSize(context,list.get(position).getApplicationCacheSizeUsage())));
    }



    /**
     * 是否在后台启动
     *
     * @param context
     * @param packageName
     * @return
     */
    private boolean isRunning(Context context, String packageName) {
        IApp_IsRun applicationManagerIsRun;
        try {
            applicationManagerIsRun = (IApp_IsRun) DeviceManagerSdk.getInstance().getApi(IApp_IsRun.class);
        } catch (DeviceManagerUnsupportException e) {
            e.printStackTrace();
            return false;
        }
        try {
            return applicationManagerIsRun.isRunning(context, packageName);
        } catch (DeviceManagerSecurityException e) {
            e.printStackTrace();

        }
        return false;
    }


    //自动z转换数据为kb/ g
    private String formatSize(Context context,long targetSize) {
        return Formatter.formatFileSize(context, targetSize);
    }



}
