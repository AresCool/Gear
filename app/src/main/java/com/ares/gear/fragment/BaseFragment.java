package com.ares.gear.fragment;

import android.support.v4.app.Fragment;

/**
 * 懒加载 BaseFragment
 * Created by Ares on 2016/9/7.
 */
public abstract class BaseFragment extends Fragment {

    /** Fragment当前状态是否可见 */
    protected boolean isVisible;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        lazyInitData();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {
    }

    protected abstract void lazyInitData();
}
