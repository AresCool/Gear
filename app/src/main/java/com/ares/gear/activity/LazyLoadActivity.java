package com.ares.gear.activity;

import android.support.v4.view.ViewPager;

import com.ares.gear.R;
import com.ares.gear.adapter.LazyFragmentAdapter;
import com.ares.gear.base.activity.BaseActibity;
import com.ares.gear.fragment.BaseFragment;
import com.ares.gear.fragment.FirseFragment;
import com.ares.gear.fragment.FourthFragment;
import com.ares.gear.fragment.SecondFragment;
import com.ares.gear.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ares on 2016/9/7.
 */
public class LazyLoadActivity extends BaseActibity {

    private ViewPager lazy_load_viewpager;

    private LazyFragmentAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.lazy_laod_activity_layout;
    }

    @Override
    protected void initGui() {
        lazy_load_viewpager = (ViewPager) findViewById(R.id.lazy_load_viewpager);
    }

    @Override
    protected void initData() {
        lazy_load_viewpager.setOffscreenPageLimit(1);
        List<BaseFragment> list = getFragmentList();
        mAdapter = new LazyFragmentAdapter(getSupportFragmentManager(), list);
        lazy_load_viewpager.setAdapter(mAdapter);
    }

    @Override
    protected void initAction() {

    }

    private List<BaseFragment> getFragmentList() {
        List<BaseFragment> list = new ArrayList<>();
        BaseFragment fragment = null;
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    fragment = new FirseFragment();
                    list.add(fragment);
                    break;
                case 1:
                    fragment = new SecondFragment();
                    list.add(fragment);
                    break;
                case 2:
                    fragment = new ThirdFragment();
                    list.add(fragment);
                    break;
                case 3:
                    fragment = new FourthFragment();
                    list.add(fragment);
                    break;
            }
            fragment = null;
        }
        return list;
    }
}
