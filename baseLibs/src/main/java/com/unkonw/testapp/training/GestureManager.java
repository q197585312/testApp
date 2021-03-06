package com.unkonw.testapp.training;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.Button;

import cn.finalteam.toolsfinal.logger.Logger;


/**
 * Created by Administrator on 2017/1/13.
 */

public class GestureManager {
    Context context;
    public VelocityTracker mVelocityTracker;

    public GestureManager(Context context) {
        this.context = context;
    }
    public void getGestureDetectorInfo(){
        final GestureDetector gestureDetector = new GestureDetector(context,new
                GestureL());
        Button mButton=new Button(context);
        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    public void startVelocityTracker(MotionEvent event) {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(event);
    }

    public int getScrollVelocity() {
        // 设置VelocityTracker单位.1000表示1秒时间内运动的像素
        mVelocityTracker.computeCurrentVelocity(1000);
        // 获取在1秒内X方向所滑动像素值
        int xVelocity = (int) mVelocityTracker.getXVelocity();
        Logger.getDefaultLogger().d(""+xVelocity);
        return Math.abs(xVelocity);
    }

    public void stopVelocityTracker() {//取消
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }
}
