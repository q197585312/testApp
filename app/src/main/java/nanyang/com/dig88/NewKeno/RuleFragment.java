package nanyang.com.dig88.NewKeno;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.Bind;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2018/9/13.
 */

public class RuleFragment extends NewKenoBaseFragment {
    @Bind(R.id.web_wv)
    WebView webView;
    String ruleUrl = "http://m.855kg.com/index.php?page=keno_new_rule&from=app&lang=";

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_new_keno_rule;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
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
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        String localLanguage = getAct().getLocalLanguage();
        if (!TextUtils.isEmpty(localLanguage)) {
            switch (localLanguage) {
                case "zh":
                    ruleUrl += "cn";
                    break;
                case "en":
                    ruleUrl += "en";
                    break;
                case "kh":
                    ruleUrl += "kh";
                    break;
                case "kr":
                    ruleUrl += "kr";
                    break;
                case "my":
                    ruleUrl += "ma";
                    break;
                case "th":
                    ruleUrl += "th";
                    break;
                case "vn":
                    ruleUrl += "vn";
                    break;
                case "in":
                    ruleUrl += "id";
                    break;
            }
        } else {
            ruleUrl += "en";
        }
        webView.loadUrl(ruleUrl);
    }
}
