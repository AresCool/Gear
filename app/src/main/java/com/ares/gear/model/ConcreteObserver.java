package com.ares.gear.model;

import com.ares.gear.interfaces.Observer;
import com.ares.gear.interfaces.Subject;
import com.ares.gear.utils.Logs;

/**
 * 具体观察者ConcreteObserver: 具体观察者可以是任何实现了Observer接口的类。观察者必须注册具体主题，一边接收更新。
 * Created by Administrator on 2016/9/19.
 */
public class ConcreteObserver implements Observer {

    private String context;
    private Subject subject;

    public ConcreteObserver(final Subject subject) {
        this.subject = subject;
        this.subject.registerObserver(this);
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public void update(String updateData) {
        Logs.i("更新数据--->" + updateData);
    }
}
