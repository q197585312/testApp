package com.nanyang.app.main.home.sport.dialog;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.api.CookieManger;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2017/4/18.
 */

public class WebPop extends BasePopupWindow {

    public WebView getWebView() {
        return webView;
    }

    private WebView webView;

    public WebPop(Context context, View v) {

        super(context, v, DeviceUtils.dip2px(context, 360), DeviceUtils.dip2px(context, 370));

    }

    public WebPop(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    private String url;

    @Override
    protected int onSetLayoutRes() {
        return R.layout.popupwindow_web_layout;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        webView = (WebView) view.findViewById(R.id.web_wv);
        webView.getSettings().setJavaScriptEnabled(true);
             /* webView.getSettings().setSupportZoom(true);          //支持缩放
            webView.getSettings().setBuiltInZoomControls(true);  //启用内置缩放装置*/
        webView.setWebViewClient(new WebViewClient());
    }


    public void setUrl(String url) {
        this.url = url;
        synCookies(url);
        webView.loadUrl(url);
    }

    public void synCookies(String url) {
        String cookie = "";
        if (CookieManger.getCookieStore().get(url) != null && CookieManger.getCookieStore().get(url).size() > 0) {
            cookie = CookieManger.getCookieStore().get(url).get(0).toString();
            AfbUtils.synCookies(context, url, cookie);
        }

    }

}
