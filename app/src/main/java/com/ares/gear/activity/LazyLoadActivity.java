package com.ares.gear.activity;

import android.support.v4.view.ViewPager;

import com.ares.gear.R;
import com.ares.gear.base.activity.BaseActibity;

/**
 * Created by Ares on 2016/9/7.
 */
public class LazyLoadActivity extends BaseActibity {

    private ViewPager lazy_load_viewpager;

    @Override
    protected int getLayout() {
        return R.layout.lazy_laod_activity_layout;
    }

    @Override
    protected void initGui() {
        lazy_load_viewpager =  (ViewPager)findViewById(R.id.lazy_load_viewpager);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAction() {

    }
}
