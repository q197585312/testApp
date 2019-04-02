package com.nanyang.app.main.home.sport.main.component;

import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.guoqi.highlightview.Component;

import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2017/8/11.
 */

public class Left2PicComponent extends Base2PicComponent {
    @Override
    protected int getAnchorDistance(View anchorView) {
        return anchorView.getLeft()+anchorView.getPaddingLeft();
    }

    @Override
    protected void setArrowLineLayoutParams(FrameLayout.LayoutParams layoutParams) {
        layoutParams.leftMargin= anchorDistance;
        layoutParams.gravity= Gravity.LEFT;
    }

    @Override
    public int getFitPosition() {
        return Component.FIT_START;
    }

    @Override
    public int getXOffset() {
        int leftX = 0;
        if(anchorView!=null)
            leftX= DeviceUtils.px2dip(anchorView.getContext(), anchorDistance);
        return -leftX;
    }
}
