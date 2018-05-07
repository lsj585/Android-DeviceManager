package com.nd.adhoc.dmsdk.demo.view;

import com.nd.adhoc.dmsdk.demo.bean.HardWareSwitchBean;

import java.util.List;

public interface DeviceControlView extends BaseView {


    void showList(List<HardWareSwitchBean> list);

    void updateView(int position);

    void jumpHome();

    void updateMsg(String msg);
}
