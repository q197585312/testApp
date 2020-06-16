package com.nanyang.app.main.home.keno;

import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.AndroidBug5497Workaround;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;

/**
 * Created by Administrator on 2017/11/24.
 */

public class WebActivity extends BaseToolbarActivity {
    WebView webView;
    private boolean canFinish;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        AndroidBug5497Workaround.assistActivity(this);
        webView = (WebView) findViewById(R.id.web_wv);
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra(AppConstant.KEY_STRING);
        canFinish = getIntent().getBooleanExtra(AppConstant.KEY_BOOLEAN, false);
        if (title == null) {
            ToastUtils.showLong(R.string.failed_to_connect);
            finish();
            return;
        }
//        toolbar.setTitle(title);
//        tvToolbarTitle.setText(title);
        toolbar.setVisibility(View.GONE);
        showLoadingDialog();
        loadWebView(url);
    }

    private void loadWebView(String url) {
        LogUtil.d("url---", "-------" + url);
        AfbUtils.synCookies(this, webView, url, true, new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    url = request.getUrl().toString();
                } else {
                    url = request.toString();
                }
                LogUtil.d("requesturl", url);
                if (url.contains("html?ispc=1"))
                    finish();
                else
                    view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//接受证书
            }
        });
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


    @Override
    public void onBackCLick(View v) {
        if (canFinish && webView.canGoBack()) {
            webView.goBack();//返回上一页面
        } else {
            super.onBackCLick(v);
        }
    }
    /*  public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            }else{
                finish();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackCLick(View v) {

        if (webView.canGoBack()) {
            webView.goBack();//返回上一页面
        } else {
            finish();
            super.onBackCLick(v);
        }
    }*/
}
