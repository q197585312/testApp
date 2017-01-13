package com.unkonw.testapp.training;

import android.view.GestureDetector;
import android.view.MotionEvent;

import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/1/13.
 */

public class GestureL implements GestureDetector.OnGestureListener {
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Logger.d("onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        System.out.println("---> 手势中的onShowPress方法");
    }
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        System.out.println("---> 手势中的onSingleTapUp方法");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        System.out.println("---> 手势中的onScroll方法");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        System.out.println("---> 手势中的onLongPress方法");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        System.out.println("---> 手势中的onFling方法");
        return false;
    }

}
