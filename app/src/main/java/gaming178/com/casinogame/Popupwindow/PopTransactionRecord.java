package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.net.http.SslError;
import android.text.TextUtils;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2020/5/21.
 */

public class PopTransactionRecord extends BasePopupWindow {
    private WebView webView;
    private String url = "";
    BaseActivity activity;
    private ImageView img_exit;
    private TextView tvTitle;

    public PopTransactionRecord(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_report;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvTitle = view.findViewById(R.id.gd_tv_pop_title);
        tvTitle.setText(context.getString(R.string.transaction_record));
        img_exit = view.findViewById(R.id.gd__img_exit);
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        String language = AppTool.getAppLanguage(context);
        if ("zh".equals(language)) {
            language = "cn";
        }
        activity = (BaseActivity) context;
        url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "transrecord.jsp" + "?Usid=" + activity.mAppViewModel.getUser().getName() + "&lng=" + language;
        webView = (WebView) view.findViewById(R.id.gd__wv_report);
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
                //handleMessage(Message msg); 其他处理
            }
        });
        synCookies(url);
        webView.loadUrl(url);
    }

    public void synCookies(String url) {
        CookieSyncManager.createInstance(context);
        CookieManager mCookieManager = CookieManager.getInstance();
        mCookieManager.setAcceptCookie(true);
        // 每次移除会有Cookie不一致问题，注释该地方
        //mCookieManager.removeSessionCookie();// 移除
        // Cookie是通过我们Volley活着HttpClient获取的
        //  Log.i(WebSiteUrl.Tag,"cookie="+mAppViewModel.getHttpClient().getCookie());
        if (activity.getApp() != null) {
            if (activity.mAppViewModel.getHttpClient() != null) {
                String cookie = activity.mAppViewModel.getHttpClient().getCookie();
                if (!TextUtils.isEmpty(cookie)) {
                    mCookieManager.setCookie(url, cookie);
                    CookieSyncManager.getInstance().sync();
                }
            }
        }
    }
}
