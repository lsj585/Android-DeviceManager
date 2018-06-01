package com.nd.adhoc.dmsdk.demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.nd.adhoc.dmsdk.demo.R;
import com.nd.adhoc.dmsdk.demo.ui.adapter.ContentFragmentPager;
import com.nd.adhoc.dmsdk.demo.base.ITabFragment;
import com.nd.adhoc.dmsdk.demo.ui.fragment.AppManagerFragment;
import com.nd.adhoc.dmsdk.demo.ui.fragment.DeviceControlManagerFragment;
import com.nd.adhoc.dmsdk.demo.ui.fragment.SystemManagerFragment;
import com.nd.adhoc.dmsdk.demo.ui.fragment.app.FileManagerFragment;
//import com.nd.adhoc.dmsdk.demo.ui.fragment.AppManagerFragment;
//import com.nd.adhoc.dmsdk.demo.ui.fragment.DeviceControlManagerFragment;
//import com.nd.adhoc.dmsdk.demo.ui.fragment.SystemManagerFragment;
//import com.nd.adhoc.dmsdk.demo.ui.fragment.app.FileManagerFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ContentFragmentPager mFragmentPager;

    private TabLayout mTableLayout;

    private ViewPager mViewPager;

    private List<ITabFragment> fragments;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mViewPager=findViewById(R.id.vp_main);
        mTableLayout=findViewById(R.id.tbl_tool_main);
        mTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initData();
        init();
    }

    private void initData(){

        if(fragments==null){
            fragments=new ArrayList<ITabFragment>();
        }else{
            fragments.clear();
        }
        fragments.add(FileManagerFragment.newInstance());
        fragments.add(AppManagerFragment.newInstance());
        fragments.add(DeviceControlManagerFragment.newInstance());
        fragments.add(SystemManagerFragment.newInstance());
    }

    private void init(){

        if(mFragmentPager==null){
            mFragmentPager=new ContentFragmentPager(getSupportFragmentManager(),this,fragments);
        }
        mViewPager.setAdapter(mFragmentPager);
        mViewPager.setOffscreenPageLimit(6);
        mTableLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
}
