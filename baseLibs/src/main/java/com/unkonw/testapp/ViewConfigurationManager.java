package com.unkonw.testapp;

import android.content.Context;
import android.view.ViewConfiguration;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class ViewConfigurationManager {
    Context context;

    public ViewConfigurationManager(Context context) {
        this.context = context;
    }
    public ViewConfiguration getViewConfiguration(){
        return ViewConfiguration.get(context);
    }
    public void getInfo(){
        ViewConfiguration viewConfiguration = getViewConfiguration();
        //获取touchSlop。该值表示系统所能识别出的被认为是滑动的最小距离
        int touchSlop = viewConfiguration.getScaledTouchSlop();
//获取Fling速度的最小值和最大值
        int minimumVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
        int maximumVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
//判断是否有物理按键
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            boolean isHavePermanentMenuKey=viewConfiguration.hasPermanentMenuKey();
        }
        //双击间隔时间.在该时间内是双击，否则是单击
        int doubleTapTimeout=ViewConfiguration.getDoubleTapTimeout();
//按住状态转变为长按状态需要的时间
        int longPressTimeout=ViewConfiguration.getLongPressTimeout();
//重复按键的时间
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB_MR1) {
            int keyRepeatTimeout=ViewConfiguration.getKeyRepeatTimeout();
        }
    }
}
