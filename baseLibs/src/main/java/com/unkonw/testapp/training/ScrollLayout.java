package com.unkonw.testapp.training;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.List;

import cn.finalteam.toolsfinal.DeviceUtils;
import cn.finalteam.toolsfinal.logger.Logger;

/**
 * Created by guolin on 16/1/12.
 */
public class ScrollLayout extends ViewGroup {

    /**
     * 用于完成滚动操作的实例
     */
    private Scroller mScroller;

    /**
     * 判定为拖动的最小移动像素数
     */
    private int mTouchSlop;

    /**
     * 手机按下时的屏幕坐标
     */
    private float mXDown;
    private float mYDown;

    /**
     * 手机当时所处的屏幕坐标
     */
    private float mXMove;
    private float mYMove;

    /**
     * 上次触发ACTION_MOVE事件时的屏幕坐标
     */
    private float mXLastMove;
    private float mYLastMove;

    /**
     * 界面可滚动的左边界
     */
    private int leftBorder;

    /**
     * 界面可滚动的右边界
     */
    private int rightBorder;
    private List<ScrollLayout> scrolls;
    private IndexChangeCallBack back;

    public int getTargetIndex() {
        return targetIndex;
    }

    private int targetIndex = 0;


    public ScrollLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 第一步，创建Scroller的实例
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        // 获取TouchSlop值
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration) + DeviceUtils.dip2px(context, 5);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // 为ScrollerLayout中的每一个子控件测量大小
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            if (childCount < 1)
                return;
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                // 为ScrollerLayout中的每一个子控件在水平方向上进行布局
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }
            // 初始化左右边界值

            leftBorder = getChildAt(0).getLeft();
            rightBorder = getRightBorder(getChildCount() - 1);
        }
    }

    private int getRightBorder(int i) {
        if (getChildAt(i).getVisibility() != GONE)
            return getChildAt(i).getRight();
        else
            return getRightBorder(i - 1);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mYDown = ev.getRawY();
                mXLastMove = mXDown;
                mYLastMove = mYDown;
                Log.d("MotionEvent", "Intercept---ACTION_DOWN:" + super.onInterceptTouchEvent(ev));
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                mYMove = ev.getRawY();
                float diff = Math.abs(mXMove - mXDown);
                mXLastMove = mXMove;
                mYLastMove = mYMove;
                // 当手指拖动值大于TouchSlop值时，认为应该进行滚动，拦截子控件的事件
                if (diff > mTouchSlop) {

                    Log.d("MotionEvent", "Intercept---ACTION_MOVE:" + true + "拦截了滑动事件----");
                    return true;
                }
                Log.d("MotionEvent", "Intercept---ACTION_MOVE:" + super.onInterceptTouchEvent(ev) + "没拦截了滑动事件----");
                break;
            case MotionEvent.ACTION_UP:

                Log.d("MotionEvent", "Intercept---ACTION_UP:" + super.onInterceptTouchEvent(ev));
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d("MotionEvent", "onTouchEvent---ACTION_DOWN:" + super.onTouchEvent(event));
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                mYMove = event.getRawY();
                int scrolledX = (int) (mXLastMove - mXMove);
                int scrolledY = (int) (mYLastMove - mYMove);
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    Log.d("MotionEvent", "onTouchEvent---ACTION_MOVE:" + true);
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    Log.d("MotionEvent", "onTouchEvent---ACTION_MOVE:" + true);
                    return true;
                }
                if (Math.abs(scrolledY) < Math.abs(scrolledX)) {
                    scrollBy(scrolledX, 0);
                    if (getParent() != null) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                mXLastMove = mXMove;
                mYLastMove = mYMove;
                Log.d("MotionEvent", "onTouchEvent---ACTION_MOVE:" + super.onTouchEvent(event));
                break;
            case MotionEvent.ACTION_UP:
                // 当手指抬起时，根据当前的滚动值来判定应该滚动到哪个子控件的界面
                targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                if (back != null) {
                    back.changePosition(targetIndex);
                }
                invalidate();
                getParent().requestDisallowInterceptTouchEvent(false);
                Log.d("MotionEvent", "onTouchEvent---ACTION_UP:" + super.onTouchEvent(event));
                break;
        }
        return true;
    }


    @Override
    public void computeScroll() {
        // 第三步，重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }


    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        if (scrolls != null) {
            for (ScrollLayout scroll : scrolls) {
                if (!scroll.equals(this)) {
                    scroll.setFollowScrolls(null);
                    scroll.scrollTo(x, y);
                }

            }
        }
    }

    public void setFollowScrolls(List<ScrollLayout> scrolls) {
        this.scrolls = scrolls;
    }

    public List<ScrollLayout> getFollowScrolls() {
        return scrolls;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void setCurrentIndex(final int index) {
        this.post(new Runnable() {
            @Override
            public void run() {
                int dx = index * getMeasuredWidth();
                // 第二步，调用startScroll()方法来初始化滚动数据并刷新界面
                scrollTo(dx, 0);
                Logger.getDefaultLogger().d(index + "滑动距离：" + dx);
//                Logger.getDefaultLogger().d("getScrollX:" + getScrollX());
//                mScroller.startScroll(getScrollX(), 0, dx, 0);
                targetIndex = index;
            }

        });

    }

    public void setIndexChangeListener(IndexChangeCallBack back) {
        this.back = back;
    }


    public interface IndexChangeCallBack {
        void changePosition(int index);
    }
}