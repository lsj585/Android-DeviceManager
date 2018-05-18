package com.nd.adhoc.dmsdk.api.huawei.manager;
import android.content.ComponentName;
import android.content.Context;
import android.util.Log;


class BaseManager {

    protected Context context ;

    private ComponentName componentName;

    public Context getContext() {

        return context;
    }

    public ComponentName getComponentName() {

        return componentName;
    }



    public void setComponentName(ComponentName name){

        this.componentName=componentName;
    }

    public void setContext(Context context) {
        this.context = context;
        Log.i(this.getClass().getName(),"context:="+context);
    }


}
