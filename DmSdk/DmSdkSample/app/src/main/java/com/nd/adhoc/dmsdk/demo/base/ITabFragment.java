package com.nd.adhoc.dmsdk.demo.base;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

/**
 * Created by HuangYK on 2018/3/16.
 */

public interface ITabFragment {

    @StringRes
    int getTabName();

    @DrawableRes
    int getDrawableId();

    Fragment getTabFragment();
}
