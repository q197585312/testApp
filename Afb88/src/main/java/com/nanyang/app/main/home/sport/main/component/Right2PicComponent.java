package com.nanyang.app.main.home.sport.main.component;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.guoqi.highlightview.Component;

import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2017/8/11.
 */

public class Right2PicComponent extends Base2PicComponent {
    @Override
    protected int getAnchorDistance(View anchorView) {
        WindowManager systemService = (WindowManager) anchorView.getContext()
                .getSystemService(Context.WINDOW_SERVICE
                );

        int width = systemService.getDefaultDisplay().getWidth();
        return width - (anchorView.getRight() + anchorView.getPaddingRight());
    }

    @Override
    protected void setArrowLineLayoutParams(FrameLayout.LayoutParams layoutParams) {
        layoutParams.rightMargin = anchorDistance;
        layoutParams.gravity = Gravity.RIGHT;
    }

    @Override
    public int getFitPosition() {
        return Component.FIT_END;
    }

    @Override
    public int getXOffset() {
        int right = 0;
        if (anchorView != null)
            right = DeviceUtils.px2dip(anchorView.getContext(), anchorDistance);
        return right;
    }
}
