package nanyang.com.dig88.Util;

import android.content.Context;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import nanyang.com.dig88.R;


/**
 * Created by Administrator on 2017/4/18.
 */

public class WebPop extends BasePopupWindow {

    private WebView webView;

    public WebPop(Context context, View v) {
        this(context, v, DeviceUtils.dip2px(context, 360), DeviceUtils.dip2px(context, 370));
    }

    public WebPop(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    public WebView getWebView() {
        return webView;
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.popupwindow_web_layout;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        webView = (WebView) view.findViewById(R.id.web_wv);

    }


    public void setUrl(String url) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
//        webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
//        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
//        webView.getSettings().setLoadWithOverviewMode(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


}
