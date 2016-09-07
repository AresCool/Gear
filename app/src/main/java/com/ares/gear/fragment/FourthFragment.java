package com.ares.gear.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ares.gear.R;
import com.ares.gear.utils.Logs;

/**
 * Created by Ares on 2016/9/7.
 */
public class FourthFragment extends BaseFragment {

    /**
     * 标志位，标志已经初始化完成
     */
    private boolean isPrepared;
    /**
     * 是否已被加载过一次，第二次就不再去请求数据了
     */
    private boolean mHasLoadedOnce;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fourth_fragment_layout, container, false);
        //TODO 加载各种数据
        isPrepared = true;
        (new Handler()).postDelayed(new Runnable() {
            @Override
            public void run() {
                lazyInitData();
            }
        }, 200);
        return v;
    }

    @Override
    protected void lazyInitData() {
        if (!isPrepared || !isVisible || mHasLoadedOnce) {
            return;
        }
        Logs.i("FourthFragment.lazyInitData is working..");
        mHasLoadedOnce = true;
    }

    @Override
    public void onDestroy() {
        Logs.i("FourthFragment.onDestroy...销毁");
        super.onDestroy();
    }
}
