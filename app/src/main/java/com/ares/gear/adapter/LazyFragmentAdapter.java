package com.ares.gear.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.BaseAdapter;

import com.ares.gear.fragment.BaseFragment;

import java.util.List;

/**
 * Created by Administrator on 2016/9/7.
 */
public class LazyFragmentAdapter extends FragmentPagerAdapter {


    private List<BaseFragment> list;

    public LazyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    public LazyFragmentAdapter(FragmentManager fm, List<BaseFragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return (list != null && list.size() > 0) ? list.size() : Integer.MAX_VALUE;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }


}
