package nanyang.com.dig88.Forex;

import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2016/10/12.
 */
public class ForexActivity extends BaseActivity {
    @BindView(R.id.web_wv)
    WebView webView;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        AppTool.setAppLanguage(mContext, "");
        setTitle(getString(R.string.forex));
        loadweb(getGameUrl());
    }

    private void loadweb(String url) {
        showBlockDialog();
        //设置WebView属性，能够执行Javascript脚本
        webView.clearCache(true);
        webView.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.setWebViewClient(new DigWebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(url);
    }

    private String getGameUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append("https://outweb094.khmergaming.com/index.php?gameid=forex");
        AppTool.setAppLanguage(mContext, "");
        String lan = AppTool.getAppLanguage(mContext);
        String lg = "en";
        if (lan.equals("zh") || lan.equals("zh_TW")) {
            lg = "cn";
        }
        builder.append("&lang=");
        builder.append(lg);
        builder.append("&token=" + getUserInfoBean().getSession_id());
        builder.append("&moblie=1");
        return builder.toString();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            return true;
        }
        return false;
    }


    //Web视图
    private class DigWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismissBlockDialog();
                }
            }, 2000);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String s) {
            webView.loadUrl(s);
            return false;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            dismissBlockDialog();
            Log.d("Game", errorCode + "," + description + "," + failingUrl);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //handler.cancel(); 默认的处理方式，WebView变成空白页
            handler.proceed();//接受证书
            dismissBlockDialog();
            //handleMessage(Message msg); 其他处理
        }
    }
}
