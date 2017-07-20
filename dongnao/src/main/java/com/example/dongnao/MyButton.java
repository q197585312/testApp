package com.example.dongnao;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/7/19.
 */

public class MyButton extends android.support.v7.widget.AppCompatButton {
    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("test","dispatchTouchEvent:acton"+event.getAction()+"---view:MyButton");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("test","onTouchEvent:acton"+event.getAction()+"---view:MyButton");
        return super.onTouchEvent(event);
    }
}
