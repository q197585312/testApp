package com.nanyang.app.main.home.keno;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.utils.LogUtil;

/**
 * Created by Administrator on 2017/11/24.
 */

public class KenoWebActivity extends BaseToolbarActivity {
    WebView webView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.web_wv);
        String url = getIntent().getStringExtra("url");
        showLoadingDialog();
        loadWebView(url);
    }

    private void loadWebView(String url) {
        LogUtil.d("url---","-------"+url);
        AfbUtils.synCookies(this ,  webView,  url);
    }
    public void onResume() {
        super.onResume();
        webView.resumeTimers();
        webView.onResume();

    }

    @Override
    public void onPause() {

        super.onPause();
        webView.onPause();
        webView.pauseTimers();
    }


    @Override
    protected void onDestroy() {

        webView.destroy();
        webView = null;
        super.onDestroy();

    }
}
