package com.ares.gear.test;

import com.ares.gear.utils.Logs;

/**
 * Created by Administrator on 2016/9/19.
 */
public class Test {

    private  int a = 0;

    private void getData() {
        Logs.i("a=" + a);
    }

    static class InnerClass {
         int b = 1;
    }
}
