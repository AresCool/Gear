package com.ares.gear.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.ares.gear.R;
import com.ares.gear.base.activity.BaseActibity;
import com.ares.gear.utils.Logs;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Administrator on 2016/9/12.
 */
public class BitmapOriginalSizeActivity extends BaseActibity {

    private static final String TAG = "LOGTAG";

    private Button btn;

    @Override
    protected int getLayout() {
        return R.layout.bitmap_original_size_activity_layout;
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initGui() {
        btn = (Button) findViewById(R.id.bitmap_original_size_bt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(0);
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    String url = "http://pic55.nipic.com/file/20141208/19462408_171130083000_2.jpg";
                    Bitmap bit = getBitmap(url);
                    if(bit!=null){
                        Log.i(TAG, "getBitmapSize: width=" + bit.getWidth() + ",height=" + bit.getHeight());
                    }
                    if (bit != null && !bit.isRecycled()) {
                        bit.recycle();
                        bit = null;
                        System.gc();
                        Log.i(TAG, "run: .......");
                    }
                }
            }).start();

        }
    };


    /**
     * 根据URL 获取网络图片大小，并且不加载到内存中来
     * @param path
     * @return
     */
    public Bitmap getBitmap(String path) {
        Bitmap bm = null;
        try {
            URL url = new URL(path);
            BitmapFactory.Options opts = new BitmapFactory.Options();
            //不加到缓存捏
            opts.inJustDecodeBounds = true;
            opts.inSampleSize=1;
            InputStream is = url.openStream();
            bm =  BitmapFactory.decodeStream(is, null, opts);
            Logs.i("--->"+opts.outWidth+","+opts.outHeight);
            if(bm!=null){
                Logs.i("--->"+bm.getWidth()+","+bm.getHeight());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bm;
    }


}
