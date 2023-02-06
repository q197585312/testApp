package nanyang.com.dig88.Activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.Bind;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/10/12.
 */
public class RTGGameActivity extends BaseActivity {
    @Bind(R.id.web_wv)
    WebView webView;
    HttpClient httpClient;

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
            }
        }
        return "en";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
        toolbar.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setTitle("绿宝石游戏");
        httpClient = new HttpClient("");
        AppTool.setAppLanguage(mContext, "");
        setDialog(new BlockDialog(mContext, getString(R.string.zhengjiazai)));
        showBlockDialog();
        LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        String name = s.getUsername();
        String loginUrl = "http://rtg.dig88api.com/login.php?web_id=" + WebSiteUrl.WebId + "&username=" + name + "&language=" + getLanguage();
        loadweb(loginUrl);
    }

    private void loadweb(String url) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);// 设置允许访问文件数据
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new DigWebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            webView.destroy();
            handler = null;
            finish();
            return true;
        }
        return false;
    }

    //Web视图
    private class DigWebViewClient extends WebViewClient {
        @Override
        public void onLoadResource(WebView view, String url) {
            super.onLoadResource(view, url);
            if (url.equals("http://afbcash.com/")) {
                view.stopLoading();
                Log.d("DigWebViewClient", "url: " + url);
            }
        }

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
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            dismissBlockDialog();
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            handler.proceed();//接受证书
            dismissBlockDialog();
        }
    }
}
