package gaming178.com.casinogame.Activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;

/**
 * Created by Administrator on 2016/5/6.
 */
public class ReportFormActivity extends BaseActivity {
    private WebView webView;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        String language = AppTool.getAppLanguage(mContext);
        setLayout.setVisibility(View.GONE);
        tvCenterTitle.setVisibility(View.VISIBLE);
        tvCenterTitle.setText(getString(R.string.report).toUpperCase());
        if ("zh".equals(language))
            language = "cn";
        url = WebSiteUrl.REPORT_URL + "?Usid=" + afbApp.getUser().getName() + "&lng=" + language;
        Log.d("GFCB", url);
//        http://112api.gd09.info/report.jsp?lng=en&Usid=TESTSHMYR02
        //http://www.gd88asia.vip/OLTGames//report.jsp?lng=en&Usid=LK00ADTEST99

        webView = (WebView) findViewById(R.id.wv_report);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
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
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                //handler.cancel(); 默认的处理方式，WebView变成空白页
                handler.proceed();//接受证书
                dismissBlockDialog();
                //handleMessage(Message msg); 其他处理
            }
        });
        synCookies(url);
        webView.loadUrl(url);
    }


    @Override   //默认点回退键，会退出Activity，需监听按键操作，使回退在WebView内发生
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_form;
    }

    private CookieManager mCookieManager;

    public void synCookies(String url) {
        CookieSyncManager.createInstance(this);
        CookieManager mCookieManager = CookieManager.getInstance();
        mCookieManager.setAcceptCookie(true);
        // 每次移除会有Cookie不一致问题，注释该地方
        //mCookieManager.removeSessionCookie();// 移除
        // Cookie是通过我们Volley活着HttpClient获取的
        //  Log.i(WebSiteUrl.Tag,"cookie="+afbApp.getHttpClient().getCookie());
        if (afbApp != null) {
            if (afbApp.getHttpClient() != null) {
                String cookie = afbApp.getHttpClient().getCookie();
                if (!TextUtils.isEmpty(cookie)) {
                    mCookieManager.setCookie(url, cookie);
                    CookieSyncManager.getInstance().sync();
                }
            }
        }
    }

    @Override
    protected void leftClick() {
        if (webView.canGoBack() && !AppTool.isShort2Click(1000)) {
            webView.goBack();
        } else{
            ViewGroup view = (ViewGroup) getWindow().getDecorView();
            view.removeAllViews();
            super.leftClick();
        }
    }
}
