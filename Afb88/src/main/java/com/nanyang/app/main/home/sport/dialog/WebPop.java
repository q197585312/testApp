package com.nanyang.app.main.home.sport.dialog;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2017/4/18.
 */

public class WebPop extends BasePopupWindow {

    public WebView getWebView() {
        return webView;
    }

    protected WebView webView;

    public WebPop(Context context, View v) {
        this(context, v, DeviceUtils.dip2px(context, 360), DeviceUtils.dip2px(context, 370));
    }

    public WebPop(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int onSetLayoutRes() {
        return R.layout.popupwindow_web_layout;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        webView = view.findViewById(R.id.web_wv);
        LinearLayout llBack = view.findViewById(R.id.ll_back_title_line);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
    }


    public void setUrl(String url) {
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        AfbUtils.synCookies(context, webView, url);
    }



}
