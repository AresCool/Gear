package com.ares.gear.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.view.View;

import com.ares.gear.R;
import com.ares.gear.utils.Logs;

/**
 * Created by Administrator on 2016/9/18.
 */
public class GifView extends View {

    /**
     * 默认为1秒
     */
    private static final int DEFAULT_MOVIE_DURATION = 1000;

    //播放动画的核心类
    private Movie mMovie;

    private int mCurrentAnimationTime = 0;

    private long mMovieStart;

    private float mScale;

    private volatile boolean mPaused = false;

    private boolean mVisible = true;


    public GifView(Context context) {
        super(context);
        mMovie = Movie.decodeStream(getResources().openRawResource(
                R.raw.test));
    }


    @Override
    protected void onDraw(Canvas canvas) {
        Logs.i("GifView----->onDraw");
        if (mMovie != null) {
            if (!mPaused) {
                updateAnimationTime();
                drawMovieFrame(canvas);
                invalidateView();
            } else {
                drawMovieFrame(canvas);
            }
        }else{
            Logs.i("onDraw-->mMovie is null");
        }
    }

    private void drawMovieFrame(Canvas canvas) {
        // 设置要显示的帧，绘制即可
        mMovie.setTime(mCurrentAnimationTime);
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        canvas.scale(500, 500);
        mMovie.draw(canvas, 0, 0);
        canvas.restore();
    }

    private void updateAnimationTime() {
        long now = android.os.SystemClock.uptimeMillis();
        // 如果第一帧，记录起始时间
        if (mMovieStart == 0) {
            mMovieStart = now;
        }
        // 取出动画的时长
        int dur = mMovie.duration();
        if (dur == 0) {
            dur = DEFAULT_MOVIE_DURATION;
        }
        // 算出需要显示第几帧
        mCurrentAnimationTime = (int) ((now - mMovieStart) % dur);
        Logs.i("当前是第"+mCurrentAnimationTime+"帧");
    }

    @SuppressLint("NewApi")
    private void invalidateView() {
        if (mVisible) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                postInvalidateOnAnimation();
            } else {
                invalidate();
            }
        }
    }
}
