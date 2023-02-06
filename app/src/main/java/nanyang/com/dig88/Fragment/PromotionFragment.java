package nanyang.com.dig88.Fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import butterknife.Bind;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.R;
import xs.com.mylibrary.allinone.util.AppTool;

/**
 * Created by Administrator on 2019/9/17.
 */

public class PromotionFragment extends BaseFragment {
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Bind(R.id.web_wv)
    WebView webView;


    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_promotion;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        tvToolbarTitle.setText(getString(R.string.promotions));
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
                showBlockDialog();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismissBlockDialog();
            }
        });
        webView.loadUrl(getPromotionsUrl());
    }

    public String getPromotionsUrl() {
        String url = BuildConfig.PromotionsUrl;
        if (BuildConfig.FLAVOR.equals("q2bet") || BuildConfig.FLAVOR.equals("ibet567")) {
            url += "&set_lang=" + getLanguage();
        }
        return url;
    }

    private String getLanguage() {
        String lg = AppTool.getAppLanguage(mContext);
        if (TextUtils.isEmpty(lg)) {
            return "en";
        } else {
            switch (lg) {
                case "en":
                    return "en";
                case "th":
                    return "th";
                case "vn":
                    return "vn";
                case "zh":
                    return "cn";
                case "in":
                    return "id";
                case "kr":
                    return "kr";
                case "my":
                    return "ma";
                case "kh":
                    return "ca";
                default:
                    return "en";
            }
        }
    }
}
