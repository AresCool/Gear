package com.ares.gear.utils;

import android.util.Log;

/**
 * 日志管理类
 * Created by Administrator on 2016/9/1.
 */
public final class Logs {

    private static final boolean IS_DEBUG = true;
    private static final String TAG = "LOGTAG";

    private Logs() {
            /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    // 下面四个是默认tag的函数
    public static void i(String msg) {
        if (IS_DEBUG)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (IS_DEBUG)
            Log.d(TAG, msg);
    }

    public static void e(String msg) {
        if (IS_DEBUG)
            Log.e(TAG, msg);
    }

    public static void v(String msg) {
        if (IS_DEBUG)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public static void i(String tag, String msg) {
        if (IS_DEBUG)
            Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (IS_DEBUG)
            Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (IS_DEBUG)
            Log.e(tag, msg);
    }

    public static void v(String tag, String msg) {
        if (IS_DEBUG)
            Log.v(tag, msg);
    }
}
