package com.nd.adhoc.dmsdk.demo.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.nd.adhoc.dmsdk.demo.base.ITabFragment;
import java.util.List;

/**
 * Created by richsjeson on 2018/1/24.
 */

public class ContentFragmentPager extends FragmentStatePagerAdapter {

    private List<ITabFragment> mFragments;
    private Context mContext;

    private ITabFragment mCurrentFragment;

    public ContentFragmentPager(FragmentManager fm, Context context, List<ITabFragment> fragments) {
        super(fm);
        this.mFragments = fragments;
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return this.mFragments.get(position).getTabFragment();
    }

    @Override
    public int getCount() {
        return this.mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (this.mFragments.get(position) != null) {
            ITabFragment fragment = this.mFragments.get(position);
            return mContext.getResources().getString(fragment.getTabName());
        }
        return "";
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment= (ITabFragment) object;
        super.setPrimaryItem(container, position, object);
    }




    public ITabFragment getCurrentFragment() {
        return mCurrentFragment;
    }
}
