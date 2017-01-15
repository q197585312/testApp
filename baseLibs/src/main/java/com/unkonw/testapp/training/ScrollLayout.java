package com.unkonw.testapp.training;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/1/14 0014.
 */

public class ScrollLayout extends ViewGroup {

    private int scaledTouchSlop;
    private Scroller scroller;
    private int leftBorder;
    private int rightBorder;
    private float rawX;
    private float lastX;
    private float rawX2;

    public ScrollLayout(Context context) {
        super(context);
    }

    public ScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
        scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();//
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        if(b){
            int childCount = getChildCount();
            for (int i4 = 0; i4 < childCount; i4++) {
                View childView = getChildAt(i);
                // 为ScrollerLayout中的每一个子控件在水平方向上进行布局
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }
        }
        leftBorder = getChildAt(0).getLeft();
        rightBorder = getChildAt(getChildCount() - 1).getRight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
           measureChild( getChildAt(i),widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction() & MotionEvent.ACTION_MASK;
       switch (action) {
           case MotionEvent.ACTION_DOWN:
               rawX = ev.getRawX();
               lastX=rawX;
               break;
           case MotionEvent.ACTION_MOVE:
               rawX2 = ev.getRawX();
               float v = rawX2 - rawX;
               lastX=rawX2;
               if(v>scaledTouchSlop) {
                   Logger.d("v>"+scaledTouchSlop);
                   return true;

               }
               break;
       }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }
}
