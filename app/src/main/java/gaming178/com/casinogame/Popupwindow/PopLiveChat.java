package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.net.http.SslError;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import gaming178.com.baccaratgame.BuildConfig;

/**
 * Created by Administrator on 2020/5/21.
 */

public class PopLiveChat extends BasePopupWindow {
    private WebView webView;
    private String url = "";
    BaseActivity activity;
    private ImageView img_exit;
    private TextView gd_tv_pop_title;

    public PopLiveChat(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_report;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        img_exit = view.findViewById(R.id.gd__img_exit);
        gd_tv_pop_title = view.findViewById(R.id.gd_tv_pop_title);
        gd_tv_pop_title.setText(context.getString(R.string.Live_Chat));
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        activity = (BaseActivity) context;
        if (BuildConfig.FLAVOR.equals("mainkasino")) {
            url = "https://secure.livechatinc.com/licence/11168507/v2/open_chat.cgi?license=11168507";
        } else if (BuildConfig.FLAVOR.equals("ratucasino88")) {
            url = "https://direct.lc.chat/11977407/1";
        } else if (BuildConfig.FLAVOR.equals("depocasino")) {
            url = "https://tawk.to/chat/5a9936514b401e45400d5958/default";
        } else if (BuildConfig.FLAVOR.equals("ularnaga")) {
            url = "https://tawk.to/chat/5a9900284b401e45400d57fa/default";
        } else if (BuildConfig.FLAVOR.equals("oricasino")) {
            url = "https://tawk.to/chat/5f5216ebf0e7167d000d68e1/default";
        } else if (BuildConfig.FLAVOR.equals("wargacasino")) {
            url = "https://direct.lc.chat/11150507/3";
        } else if (BuildConfig.FLAVOR.equals("rajabakarat")) {
            url = "https://direct.lc.chat/8846269/";
        } else if (BuildConfig.FLAVOR.equals("idrkasino")) {
            url = "https://direct.lc.chat/8863379/";
        } else if (BuildConfig.FLAVOR.equals("rajacasino")) {
            url = "https://direct.lc.chat/8843331/";
        } else if (BuildConfig.FLAVOR.equals("memoricasino")) {
            url = "https://direct.lc.chat/11077682/";
        } else if (BuildConfig.FLAVOR.equals("indkasino")) {
            url = "https://direct.lc.chat/8837176/";
        }
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
        webView.loadUrl(url);
    }
}
