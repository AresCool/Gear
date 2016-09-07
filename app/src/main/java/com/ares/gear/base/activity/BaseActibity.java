package com.ares.gear.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.ares.gear.R;

/**
 * Created by Ares on 2016/9/7.
 */
public abstract class BaseActibity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayout());
        initGui();
        initData();
        initAction();
    }

    protected abstract int getLayout();

    protected abstract void initGui();

    protected abstract void initData();

    protected abstract void initAction();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
