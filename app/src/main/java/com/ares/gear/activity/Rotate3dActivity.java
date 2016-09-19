package com.ares.gear.activity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.ares.gear.R;
import com.ares.gear.base.activity.BaseActibity;
import com.ares.gear.utils.Logs;
import com.ares.gear.view.Rotate3dAnimation;

/**
 * 3D动画旋转
 * Created by Administrator on 2016/9/12.
 */
public class Rotate3dActivity extends BaseActibity {

    private ImageView image;
    private Button start, stop;
    private Rotate3dAnimation rotation;
    private StartNextRotate startNext;


    @Override
    protected int getLayout() {
        return R.layout.rotate_3d_activity_layout;
    }

    @Override
    protected void initGui() {
        image = (ImageView) findViewById(R.id.image);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        start.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                //进行360度的旋转
                startRotation(0, 360);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                image.clearAnimation();
            }
        });

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void initAction() {
    }

    private void startRotation(float start, float end) {
        // 计算中心点
        final float centerX = image.getWidth() / 2.0f;
        final float centerY = image.getHeight() / 2.0f;
        Logs.d("centerX=" + centerX + ", centerY=" + centerY);
        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        //final Rotate3dAnimation rotation =new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        //Z轴的缩放为0
        rotation = new Rotate3dAnimation(start, end, centerX, centerY, 0f, true);
        rotation.setDuration(2000);
        rotation.setFillAfter(true);
        //rotation.setInterpolator(new AccelerateInterpolator());
        //匀速旋转
        rotation.setInterpolator(new LinearInterpolator());
        //设置监听
        startNext = new StartNextRotate();
        rotation.setAnimationListener(startNext);
        image.startAnimation(rotation);
    }

    private class StartNextRotate implements Animation.AnimationListener {

        public void onAnimationEnd(Animation animation) {
            image.startAnimation(rotation);
        }

        public void onAnimationRepeat(Animation animation) {

        }

        public void onAnimationStart(Animation animation) {

        }

    }
}