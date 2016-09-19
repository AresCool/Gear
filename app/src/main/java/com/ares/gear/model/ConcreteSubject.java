package com.ares.gear.model;

import com.ares.gear.interfaces.Observer;
import com.ares.gear.interfaces.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体可观察者ConcreteSubject: 一个具体主题实现了主题接口，除了注册和撤销之外，具体主题还实现了notifyObservers()方法，这个方法用来在主题状态改变时更新所有观察者。具体主题也可能有设置和获取状态的方法。
 * Created by Administrator on 2016/9/19.
 */
public class ConcreteSubject implements Subject {

    private final List<Observer> observers;
    private String context;


    public ConcreteSubject() {
        this.observers = new ArrayList<Observer>();
    }

    private void temperatureChanged() {
        this.notifyObservers();
    }

    public void setTemperature(final String context) {
        this.context = context;
        this.temperatureChanged();
    }


    @Override
    public void notifyObservers() {
        for (final Observer o : observers) {
            o.update(context);
        }
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        if (observers.indexOf(o) >= 0) {
            observers.remove(o);
        }
    }
}
