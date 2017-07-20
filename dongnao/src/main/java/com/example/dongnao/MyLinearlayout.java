package com.example.dongnao;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2017/7/19.
 */

public class MyLinearlayout extends LinearLayout{
    public MyLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.i("test","dispatchTouchEvent:acton"+event.getAction()+"---view:MyLinearlayout");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("test","onTouchEvent:acton"+event.getAction());
        return super.onTouchEvent(event);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i("test","onInterceptTouchEvent:acton"+ev.getAction()+"---view:MyLinearlayout");
        return super.onInterceptTouchEvent(ev);
    }
}
