package com.example.dongnao;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/7/26.
 */

public class MyScorllView extends ScrollView {
    public MyScorllView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        //不要拦截event
        requestDisallowInterceptTouchEvent(false);
        return super.dispatchTouchEvent(ev);
    }
}
