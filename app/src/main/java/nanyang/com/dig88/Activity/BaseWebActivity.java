package nanyang.com.dig88.Activity;

import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2018/1/10.
 */

public abstract class BaseWebActivity extends BaseActivity {
    public WebView webView;
    public LinearLayout llParent;

    public abstract int getWebViewId();

    @Override
    public void initView() {
        super.initView();
        llParent = (LinearLayout) findViewById(R.id.ll_parent);
        webView = (WebView) findViewById(getWebViewId());
        initWebView();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            back();
            return true;
        }
        return false;
    }

    private void back() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        finish();
    }

    @Override
    protected void leftClick() {
        back();
    }

    public void initWebView() {
        //设置WebView属性，能够执行Javascript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.getSettings().setAppCacheEnabled(true);//是否使用缓存
        webView.getSettings().setDomStorageEnabled(true);//DOM Storage
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                onStartedUrl(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                onFinishedLoad(view, url);
            }
        });
    }

    public abstract void onStartedUrl(WebView view, String url, Bitmap favicon);

    public abstract void onFinishedLoad(WebView view, String url);
}
