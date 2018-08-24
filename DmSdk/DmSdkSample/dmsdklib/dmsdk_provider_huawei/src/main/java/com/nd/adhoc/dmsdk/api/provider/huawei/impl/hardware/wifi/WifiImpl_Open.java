package com.nd.adhoc.dmsdk.api.provider.huawei.impl.hardware.wifi;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;

import com.nd.adhoc.dmsdk.IDmSdkApi;
import com.nd.adhoc.dmsdk.annotation.ApiImpl;
import com.nd.adhoc.dmsdk.api.hardware.wifi.IWifi_Open;
import com.nd.sdp.android.serviceloader.annotation.Service;

@Service(IDmSdkApi.class)
@ApiImpl(IWifi_Open.class)
public class WifiImpl_Open extends WifiImpl_Base implements IWifi_Open {
    @Override
    public boolean open(@NonNull Context context){
        derall(context,true);
        enableWifi(context);
        return true;
    }


    @Override
    public void release(@NonNull Context context) {

    }
    /**
     * 打开WIFI开关--- TODO ZYB后续要提取类似这种的方法
     * @param context
     */
    private void enableWifi(@NonNull Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        wm.setWifiEnabled(true);
    }
}
