package com.example.dongnao;

import android.content.Context;
import androidx.viewpager.widget.ViewConfigurationCompat;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import static android.view.KeyEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;

/**
 * Created by Administrator on 2017/7/26.
 */

public class MyViewPager extends ViewGroup {
    private Context context;
    private int scaledPagingTouchSlop;
    private Scroller scroller;
    private float downX;
    private float moveX;
    private float xDoff;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initViewPager();
    }

    private void initViewPager() {

        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        scaledPagingTouchSlop = viewConfiguration.getScaledPagingTouchSlop();
        scroller = new Scroller(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case ACTION_DOWN:
                 downX = ev.getRawX();
                break;
            case ACTION_MOVE:
                moveX=ev.getRawX();
                xDoff=Math.abs(moveX-downX);
                if(xDoff>scaledPagingTouchSlop)
                    return true;
                break;

        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
