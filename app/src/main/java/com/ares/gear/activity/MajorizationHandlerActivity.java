package com.ares.gear.activity;

import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

import com.ares.gear.R;
import com.ares.gear.base.activity.BaseActibity;
import com.ares.gear.interfaces.Observer;
import com.ares.gear.model.ConcreteObserver;
import com.ares.gear.model.ConcreteSubject;

import java.lang.ref.WeakReference;

/**
 * 异步请求任务(采用软引用)
 * Created by Administrator on 2016/9/19.
 */
public class MajorizationHandlerActivity extends BaseActibity {

    private Button majorization_handler_button, majorization_Observer_button;
    private TextView majorization_handler_textview;

    private MyHandler mHandler = null;


    @Override
    protected int getLayout() {
        return R.layout.majorization_handler_activity_layout;
    }

    @Override
    protected void initGui() {
        majorization_handler_button = (Button) findViewById(R.id.majorization_handler_button);
        majorization_Observer_button = (Button) findViewById(R.id.majorization_Observer_button);
        majorization_handler_textview = (TextView) findViewById(R.id.majorization_handler_textview);
    }

    @Override
    protected void initData() {
        mHandler = new MyHandler(this);
    }

    @Override
    protected void initAction() {
        majorization_handler_button.setOnClickListener((v) -> execute());
        majorization_Observer_button.setOnClickListener(v -> executeObserver());
    }

    private void execute() {
        mHandler.sendEmptyMessage(0);
    }

    private static class MyHandler extends Handler {

        private WeakReference<MajorizationHandlerActivity> reference;

        public MyHandler(MajorizationHandlerActivity context) {
            reference = new WeakReference<MajorizationHandlerActivity>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    MajorizationHandlerActivity act = (reference == null ? null : reference.get());
                    if (act == null || act.isFinishing())
                        return;
                    act.majorization_handler_textview.setText("你麻麻喊你回家吃饭");
                    break;
            }
        }
    }

    private void executeObserver() {//执行观察者方法
        ConcreteSubject  mSubject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserver(mSubject);
        Observer observer2 = new ConcreteObserver(mSubject);
        Observer observer3 = new ConcreteObserver(mSubject);
        mSubject.setTemperature("我不会回去吃饭了。");
        mSubject.removeObserver(observer1);
        mSubject.removeObserver(observer2);
        mSubject.removeObserver(observer3);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
