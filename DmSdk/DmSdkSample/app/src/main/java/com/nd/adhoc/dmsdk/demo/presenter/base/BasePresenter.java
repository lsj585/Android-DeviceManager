package com.nd.adhoc.dmsdk.demo.presenter.base;

import android.content.Context;

import com.nd.adhoc.dmsdk.demo.model.BaseModel;
import com.nd.adhoc.dmsdk.demo.view.BaseView;

public abstract class BasePresenter<T extends BaseView,E extends BaseModel>{


    protected T view;

    protected Context context;

    protected E modle;


    public BasePresenter(Context context, T view){

        this.view=view;
        this.context=context;
    }


    public void setModle(E modle){

        this.modle=modle;
    }


    public void checkViewNotNull() {
        if (null == view) {
            throw new RuntimeException("In BasePresenter,mView mustn't be null!");
        }
    }

    public void onDestroy() {
        this.view = null;
        this.context=null;
    }
}
