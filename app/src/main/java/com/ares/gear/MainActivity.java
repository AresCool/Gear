package com.ares.gear;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.ares.gear.activity.FaceBookFrescoActivity;
import com.ares.gear.activity.LazyLoadActivity;
import com.ares.gear.activity.ShaderEffectActivity;
import com.ares.gear.adapter.MainRecycleAdapter;
import com.ares.gear.base.activity.BaseActibity;
import com.ares.gear.decoration.DividerItemDecoration;
import com.ares.gear.interfaces.MainRecycleItemListener;
import com.ares.gear.utils.Logs;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActibity implements MainRecycleItemListener {

    private RecyclerView mRecyclerView;

    private MainRecycleAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initGui() {
        mRecyclerView = (RecyclerView) findViewById(R.id.title_list_recycleview);
    }

    @Override
    protected void initData() {
        List<String> list = getTitleList();
        mAdapter = new MainRecycleAdapter(this, list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(this);
        mAdapter.setClickLongListener(this);
    }

    @Override
    protected void initAction() {

    }

    private List<String> getTitleList() {
        Resources res = getResources();
        String[] titleRes = res.getStringArray(R.array.titleList);
        return Arrays.asList(titleRes);
    }

    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = null;
        switch (postion) {
            case 0:
                intent = new Intent(MainActivity.this, LazyLoadActivity.class);
                break;
            case 1:
                intent = new Intent(MainActivity.this, FaceBookFrescoActivity.class);
                break;
            case 2:
                intent = new Intent(MainActivity.this, ShaderEffectActivity.class);
                break;
            default:
                break;
        }
        if (intent != null)
            startActivity(intent);
    }

    @Override
    public void onItemLongClick(View view, int postion) {
        Logs.i("onItemLongClick-->" + postion);
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
