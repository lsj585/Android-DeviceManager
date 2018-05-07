package com.nd.adhoc.dmsdk.demo.view;

import com.nd.adhoc.dmsdk.demo.bean.ApplicationInfoBean;
import com.nd.adhoc.dmsdk.demo.bean.FileInfoBean;

import java.util.List;

public interface AppManagerView extends BaseView {


    void showList(List<ApplicationInfoBean> list);

    void updateView(int viewPosition);

    void removeUpdate(int viewPosition);

    void updateMsg(String msg);
}
