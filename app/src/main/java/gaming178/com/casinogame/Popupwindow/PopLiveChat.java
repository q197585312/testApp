package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.net.http.SslError;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import gaming178.com.baccaratgame.BuildConfig;

/**
 * Created by Administrator on 2020/5/21.
 */

public class PopLiveChat extends BasePopupWindow {
    BaseActivity activity;
    RelativeLayout rl_title;
    private WebView webView;
    private String url = "";
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
        rl_title = view.findViewById(R.id.rl_title);
        if (!BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
            rl_title.setBackgroundColor(ContextCompat.getColor(context, R.color.login_color));
        }
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

        url = activity.mAppViewModel.getLiveChatStr();

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
