package com.nanyang.app.main.home.keno;

import android.content.pm.ActivityInfo;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

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
        //-https://www.onlinegames22.com/player/login/apiLogin0?userId=209122&agentId=a8&tokenId=gSxwaalV3647bSfRd7Sg%2F4qvfASgPPSdYntO%2B77guMA%3D&isMobileLogin=True&isShowSymbol=true&currencySymbol=&isApp=false&externalURL=https%3A%2F%2Fafb1188.com&gameCode=MX-LIVE-001&platform=SEXYBCRT&gameType=LIVE&isLaunchGame=true&language=cn
        //2020-09-29 10:49:44.922 22541-22541/com.nanyang.afb1188 D/ActivityThread:
        if(url.contains("www.onlinegames22.com")){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            // 设置为跟随系统sensor的状态
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
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
                ///_View/LiveDealerGDC.aspx?time=1596692876201
                if (url.contains("html?ispc=1"))
                    finish();
                else if (url.contains("/LiveDealerGDC.aspx")) {
                    goGd88App();
                } else {
                    view.loadUrl(url);
                }
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//接受证书
            }
        });
    }

    private void goGd88App() {
        getSkipGd88Data();
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
