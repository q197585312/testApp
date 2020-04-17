package com.nanyang.app.main.home.keno;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;

/**
 * Created by Administrator on 2017/11/24.
 */

public class WebActivity extends BaseToolbarActivity {
    WebView webView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.web_wv);
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra(AppConstant.KEY_STRING);
        if (title == null) {
            ToastUtils.showLong(R.string.failed_to_connect);
            finish();
            return;
        }
        tvToolbarTitle.setText(title);
        tvToolbarRight.setVisibility(View.GONE);
        showLoadingDialog();
        loadWebView(url);
    }

    private void loadWebView(String url) {
        LogUtil.d("url---", "-------" + url);
        AfbUtils.synCookies(this, webView, url);
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
