package com.nd.adhoc.dmsdk.demo.model;

import android.content.Context;

import com.nd.adhoc.dmsdk.demo.bean.BaseBean;

import java.util.List;

public abstract class BaseModel <T extends BaseBean> {

    protected Context context;

    public BaseModel(Context context){

        this.context=context;
    }
    /**
     *获取按键列表
     * @return
     */
   public abstract List<T> getList();

   public abstract void update(T t,int position);

   public abstract void delete(T t);

   public abstract T findById(long id);

   public abstract void update(int position);

   public abstract void release();

}
