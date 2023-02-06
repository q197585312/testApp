package nanyang.com.dig88.Activity;

import android.content.res.Configuration;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import butterknife.Bind;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2017/9/4.
 */

public class Gd88WebActivity extends BaseActivity {
    @Bind(R.id.web_wv)
    WebView webView;
    @Bind(R.id.ll_parent)
    LinearLayout llParent;
    String loadUrl;
    String urlParmars;
    boolean isLoading;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        llParent.setBackgroundResource(R.mipmap.base_bg);
        toolbar.setBackgroundResource(0);
        setleftViewEnable(true);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            toolbar.setVisibility(View.VISIBLE);
        }
        setTitle(getString(R.string.gd_live_entertainment));
        loadUrl = getIntent().getStringExtra("url");
        urlParmars = getIntent().getStringExtra("parmas");
        Log.d("Aser", "loadUrl:" + loadUrl);
        Log.d("Aser", "urlParmars:" + urlParmars);
        isLoading = getIntent().getBooleanExtra("loading", false);
        loadweb();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
        }
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            toolbar.setVisibility(View.GONE);
        } else {
            toolbar.setVisibility(View.VISIBLE);
        }
    }

    private void loadweb() {
        //设置WebView属性，能够执行Javascript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.postUrl(loadUrl, urlParmars.getBytes());
        setDialog(new BlockDialog(this, getString(R.string.zhengjiazai)));
        if (!isLoading) {
            showBlockDialog();
        }
        //设置Web视图
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new DigWebViewClient());
    }

    //Web视图
    private class DigWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            dismissBlockDialog();
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            dismissBlockDialog();
            Log.d("Game", errorCode + "," + description + "," + failingUrl);
        }

//        @Override
//        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//            //handler.cancel(); 默认的处理方式，WebView变成空白页
//            handler.proceed();//接受证书
//            dismissBlockDialog();
//            //handleMessage(Message msg); 其他处理
//        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            handler.proceed();//接受证书
            dismissBlockDialog();
        }

//        @Override
//        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, com.tencent.smtt.export.external.interfaces.SslError sslError) {
//            super.onReceivedSslError(webView, sslErrorHandler, sslError);
//            //handler.cancel(); 默认的处理方式，WebView变成空白页
//            sslErrorHandler.proceed();//接受证书
//            dismissBlockDialog();
//            //handleMessage(Message msg); 其他处理
//        }
    }
}
