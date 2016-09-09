package com.ares.gear.activity;

import com.ares.gear.R;
import com.ares.gear.base.activity.BaseActibity;
import com.ares.gear.view.StrokeTextView;

/**
 * 描边字体
 * Created by Administrator on 2016/9/9.
 */
public class ShaderEffectActivity extends BaseActibity {

    private StrokeTextView mStrokeTextView;

    @Override
    protected int getLayout() {
        return R.layout.shader_effect_activity_layout;
    }

    @Override
    protected void initGui() {
        mStrokeTextView = (StrokeTextView) findViewById(R.id.test_stroketextview);
        mStrokeTextView.setOutTextViewColor(R.color.black);
        mStrokeTextView.setOutTextViewSize(3);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAction() {

    }
}
