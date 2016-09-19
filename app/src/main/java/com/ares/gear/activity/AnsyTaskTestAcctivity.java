package com.ares.gear.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ares.gear.R;
import com.ares.gear.base.activity.BaseActibity;
import com.ares.gear.utils.Logs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 异步任务、
 * Created by Administrator on 2016/9/13.
 */
public class AnsyTaskTestAcctivity extends BaseActibity {

    private Button ansy_task_test_btn;

    private ProgressDialog progressDialog;

    private ImageView show_iamgeView;

    @Override
    protected int getLayout() {
        return R.layout.ansytask_test_activity_layout;
    }

    @Override
    protected void initGui() {
        ansy_task_test_btn = (Button) findViewById(R.id.ansy_task_test_btn);
        show_iamgeView = (ImageView) findViewById(R.id.show_iamgeView);
    }

    @Override
    protected void initData() {
        progressDialog = new ProgressDialog(AnsyTaskTestAcctivity.this);
        progressDialog.setTitle("提示信息");
        progressDialog.setMessage("正在下载中，请稍后......");
        //    设置setCancelable(false); 表示我们不能取消这个弹出框，等下载完成之后再让弹出框消失
        progressDialog.setCancelable(false);
        //    设置ProgressDialog样式为圆圈的形式
        //progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        //    设置ProgressDialog样式为水平的样式
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    }

    @Override
    protected void initAction() {
        ansy_task_test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeTask();
            }
        });
    }

    private void executeTask() {
        new MyAsyncTask().execute("http://img.hb.aicdn.com/1a10f1f34ab022b530fed27f807d5a89188433fef5f3-rFEISj_fw580");
    }

    /**
     * 定义一个类，让其继承AsyncTask这个类
     * Params: String类型，表示传递给异步任务的参数类型是String，通常指定的是URL路径
     * Progress: Integer类型，进度条的单位通常都是Integer类型
     * Result：byte[]类型，表示我们下载好的图片以字节数组返回
     *
     * @author xiaoluo
     */
    public class MyAsyncTask extends AsyncTask<String, Integer, byte[]> {

        @Override
        protected void onPreExecute() {//初始化一些东西,例如dialog
            super.onPreExecute();
            Logs.i("MyAsyncTask.onPreExecute--------------");
            progressDialog.show();
        }

        @Override
        protected byte[] doInBackground(String... params) {
            String testValue = params[0];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] image = new byte[]{};
            HttpURLConnection conn = null;
            InputStream is = null;
            try {
                URL url = new URL(testValue);
                conn = (HttpURLConnection) url.openConnection();
                //设置超时的时间，5000毫秒即5秒
                conn.setConnectTimeout(5000);
                //设置获取图片的方式为GET
                conn.setRequestMethod("GET");
                //响应码为200，则访问成功
                if (conn.getResponseCode() == 200) {

                    //    得到文件的总长度
                    long file_length = conn.getContentLength();
                    //    每次读取后累加的长度
                    long total_length = 0;
                    int length = 0;
                    //    每次读取1024个字节
                    byte[] data = new byte[1024];
                    //获取连接的输入流，这个输入流就是图片的输入流
                    is = conn.getInputStream();
                    while (-1 != (length = is.read(data))) {
                        //    每读一次，就将total_length累加起来
                        total_length += length;
                        //    边读边写到ByteArrayOutputStream当中
                        byteArrayOutputStream.write(data, 0, length);
                        //    得到当前图片下载的进度
                        int progress = ((int) (total_length / (float) file_length) * 100);
                        //    时刻将当前进度更新给onProgressUpdate方法
                        publishProgress(progress);
                    }
                }
                image = byteArrayOutputStream.toByteArray();
                is.close();
                byteArrayOutputStream.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                conn.disconnect();
            }
            return image;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(byte[] result) {
            super.onPostExecute(result);
            if (result != null) {
                //    将doInBackground方法返回的byte[]解码成要给Bitmap
                Bitmap bitmap = BitmapFactory.decodeByteArray(result, 0, result.length);
                //    更新我们的ImageView控件
                show_iamgeView.setImageBitmap(bitmap);
                //    使ProgressDialog框消失
                show_iamgeView.setBackground(new BitmapDrawable(bitmap));
            }
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }


}
