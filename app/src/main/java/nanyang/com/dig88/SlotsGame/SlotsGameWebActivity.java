package nanyang.com.dig88.SlotsGame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import butterknife.Bind;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.Dig88Utils;

/**
 * Created by 47184 on 2019/7/4.
 */

public class SlotsGameWebActivity extends BaseActivity {
    @Bind(R.id.web_wv)
    WebView webView;
    @Bind(R.id.web_left_back)
    ImageView webLeftBack;
    String fishType;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar.setVisibility(View.GONE);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        webLeftBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        if (url.startsWith("https://pgslots")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        fishType = intent.getStringExtra("Fish");
        initWebView();
        webView.loadUrl(url);
    }

    private void goBack() {
        if (webView != null) {
            webView.destroy();
        }
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        finish();
    }

    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
//        webView.getSettings().setAppCacheEnabled(false);//是否使用缓存
//        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebChromeClient(new WebChromeClient());
        if (!TextUtils.isEmpty(fishType)) {
            if (fishType.equals("YuLeWuQiong")) {
                webView.getSettings().setDomStorageEnabled(true);//DOM Storage
                webView.getSettings().setAllowFileAccess(true);
            } else if (fishType.equals("Joker123")) {
                webView.getSettings().setDomStorageEnabled(true);
            }
        }
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                if (webLeftBack != null) {
                    webLeftBack.setVisibility(View.VISIBLE);
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //handler.cancel(); 默认的处理方式，WebView变成空白页
                handler.proceed();//接受证书
                //handleMessage(Message msg); 其他处理
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Dig88Utils.setLang(mContext);
    }
}
