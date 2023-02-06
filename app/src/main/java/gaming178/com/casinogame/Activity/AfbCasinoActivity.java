package gaming178.com.casinogame.Activity;

import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import butterknife.BindView;
import gaming178.com.baccaratgame.R;
import gaming178.com.baccaratgame.R2;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;

/**
 * Created by Administrator on 2018/4/18.
 */

public class AfbCasinoActivity extends BaseActivity {
    @BindView(R2.id.gd__title)
    View title;
    @BindView(R2.id.gd__web_wv)
    WebView webView;
    @BindView(R2.id.gd__img_exit)
    ImageView img_exit;
    String url;

    @Override
    protected int getLayoutRes() {
        return R.layout.gd_activity_web;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        isAttached = true;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onAttachedToWindow();
        isAttached = false;
    }

    @Override
    protected void initView() {
        super.initView();
        title.setVisibility(View.GONE);
        img_exit.setVisibility(View.GONE);
    }

    public void goBack() {
        webView.reload();
        webView.removeAllViews();
        webView.destroy();
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        leftClick();
    }

    protected void leftClick() {
        skipAct(LobbyActivity.class);
        finish();
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            goBack();
            return true;
        }
        return false;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        new Thread() {
            @Override
            public void run() {
                if (mAppViewModel.getHttpClient() == null) {
                    return;
                }
                String requestUrl = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "afbcasinopath.jsp";
                String result = mAppViewModel.getHttpClient().sendPost(requestUrl, "liveLotteryType=MP");
                if (result.startsWith("Results=ok")) {
                    String[] split = result.split("#");
                    url = split[1];
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            load();
                        }
                    });
                }
            }
        }.start();
    }

    public void load() {
        if (webView == null) {
            return;
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webView.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        webView.getSettings().setSupportZoom(false);//是否可以缩放，默认true
        webView.getSettings().setBuiltInZoomControls(false);//是否显示缩放按钮，默认false
        webView.getSettings().setDisplayZoomControls(false);

        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.getSettings().setAppCacheEnabled(true);//是否使用缓存
        webView.getSettings().setDomStorageEnabled(true);//DOM Storage
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

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
        webView.loadUrl(url);
    }

//    @Override
//    public boolean isCanSlideChangeTable() {
//        if (!TextUtils.isEmpty(gameType) && gameType.equals("SLOTS")) {
//            return true;
//        } else {
//            return false;
//        }
//    }
}
