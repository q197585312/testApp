package com.nanyang.app.main.home.sport.main.component;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.guoqi.highlightview.Component;
import com.nanyang.app.R;

import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by binIoter on 16/6/17.
 */
public abstract class Base2PicComponent implements Component {

    protected View anchorView;
    protected int anchorDistance;
    protected int arrowLinePicRes;
    protected int contentPicRes;
    protected int contentMargin;

    public void setAnchorView(View view) {
        this.anchorView = view;

        if (anchorView != null) {
            anchorDistance =getAnchorDistance(anchorView);
        } else
            this.anchorDistance = 0;
    }

    protected abstract int getAnchorDistance(View anchorView);

    public void setPicRes(int arrowLinePicRes,int contentPicRes){
        this.arrowLinePicRes = arrowLinePicRes;
        this.contentPicRes = contentPicRes;
    }
    public void setContentMarginLeftRight(int margin){
        this.contentMargin = margin;
    }

    @Override
    public View getView(LayoutInflater inflater) {
        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.gudile_layer_pic2, null);
        ImageView iv1 = (ImageView) ll.findViewById(R.id.guide_line_iv);
        ImageView iv2 = (ImageView) ll.findViewById(R.id.guide_content_iv);
        if(arrowLinePicRes>0){
            iv1.setImageResource(arrowLinePicRes);
        }
        if(contentPicRes>0){
            iv2.setImageResource(contentPicRes);
        }
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) iv1.getLayoutParams();
        setArrowLineLayoutParams(layoutParams);
//        layoutParams.leftMargin= anchorDistance;
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) iv2.getLayoutParams();

        layoutParams2.leftMargin= DeviceUtils.px2dip(iv2.getContext(), contentMargin);
        layoutParams2.rightMargin=DeviceUtils.px2dip(iv2.getContext(), contentMargin);
        return ll;
    }

    protected abstract void setArrowLineLayoutParams(FrameLayout.LayoutParams layoutParams);

    @Override
    public int getAnchor() {
        return Component.ANCHOR_BOTTOM;
    }

/*    @Override
    public int getFitPosition() {
        return Component.FIT_START;
    }

    @Override
    public int getXOffset() {
        Log.d("XS"," getXOffset===="+ -anchorDistance);
        int leftX = 0;
        if(anchorView!=null)
            leftX=DeviceUtils.px2dip(anchorView.getContext(), anchorDistance);
        return -leftX;
    }*/

    @Override
    public int getYOffset() {
        return 10;
    }
}
