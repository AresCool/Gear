package com.ares.gear.interfaces;

/**
 * 可观察者Subject: 主题接口，即可观察者Observable，对象使用此接口注册为观察者，或者把自己从观察者中删除，每个主题可以有多个观察者。
 * Created by Administrator on 2016/9/19.
 */
public interface Subject {

    void registerObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();

}
