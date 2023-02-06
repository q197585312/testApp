package nanyang.com.dig88.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import butterknife.BindView;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2018/3/13.
 */

public abstract class BaseWebFragment extends BaseFragment {
    @BindView(R.id.wv_promotions)
    WebView webView;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_lucky_roulette;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webView.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.getSettings().setAppCacheEnabled(true);//是否使用缓存
        webView.getSettings().setDomStorageEnabled(true);//DOM Storage
        webView.setWebViewClient(new DigWebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(getLoadUrl());
    }

    abstract String getLoadUrl();

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
    }

    private class DigWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            dismissBlockDialog();
            Log.d("Game", errorCode + "," + description + "," + failingUrl);
        }

    }
}
