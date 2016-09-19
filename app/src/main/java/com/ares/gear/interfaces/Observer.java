package com.ares.gear.interfaces;

/**
 * 所有潜在的观察者必须实现观察者接口，这个接口只有update方法，当主题改变时，它被调用。
 * Created by Administrator on 2016/9/19.
 */
public interface Observer {

    public void update(String updateData);
}
