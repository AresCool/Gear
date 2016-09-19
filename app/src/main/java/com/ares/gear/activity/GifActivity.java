package com.ares.gear.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ares.gear.view.GifView;

/**
 * gif动画
 * Created by Administrator on 2016/9/18.
 */
public class GifActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GifView(this));
    }
}
