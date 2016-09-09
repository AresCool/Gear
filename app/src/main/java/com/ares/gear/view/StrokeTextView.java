package com.ares.gear.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ares.gear.R;
import com.ares.gear.utils.Logs;

/**
 * 自定义view   字体描边
 * Created by Administrator on 2016/9/9.
 */
public class StrokeTextView extends TextView {

    private TextView outlineTextView = null;

    private Context mContext;

    private int index = 0;
    private int textColor = 0;

    public StrokeTextView(Context context) {
        super(context);
        mContext = context;
        outlineTextView = new TextView(context);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
        outlineTextView = new TextView(context, attrs);
        init();
    }

    public StrokeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        outlineTextView = new TextView(context, attrs, defStyle);
        TypedArray mTypedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StrokeTextView, defStyle, 0);
        index = mTypedArray.getInt(mTypedArray.getIndex(1), 0);
        textColor = mTypedArray.getColor(mTypedArray.getIndex(0), Color.BLACK);
        Logs.i("index=" + index + ",textColor=" + textColor);
        init();
    }

    public void setOutTextViewColor(int textColor) {
        Message message = new Message();
        message.what = 0;
        message.obj = textColor;
        mHandler.sendMessage(message);
    }

    public void setOutTextViewSize(int textSize) {
        Message message = new Message();
        message.what = 1;
        message.obj = textSize;
        mHandler.sendMessage(message);
    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    int textColor = (Integer) msg.obj;
                    outlineTextView.setTextColor(textColor);// 描边颜色
                    break;
                case 1:
                    int textSize = (Integer) msg.obj;
                    TextPaint paint = outlineTextView.getPaint();
                    paint.setStrokeWidth(textSize);// 描边宽度
                    break;
            }
        }
    };

    public void init() {
        TextPaint paint = outlineTextView.getPaint();
        paint.setStrokeWidth(index);// 描边宽度
        paint.setStyle(Paint.Style.STROKE);
        outlineTextView.setTextColor(textColor);// 描边颜色
        outlineTextView.setGravity(getGravity());
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
        outlineTextView.setLayoutParams(params);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 设置轮廓文字
        CharSequence outlineText = outlineTextView.getText();
        if (outlineText == null || !outlineText.equals(this.getText())) {
            outlineTextView.setText(getText());
            postInvalidate();
        }
        outlineTextView.measure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        outlineTextView.layout(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        outlineTextView.draw(canvas);
        super.onDraw(canvas);
    }
}