package nanyang.com.dig88.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.google.gson.Gson;

import butterknife.Bind;
import nanyang.com.dig88.Entity.GameMaintenanceBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2019/3/19.
 */

public class BaseWebGameActivity extends BaseActivity {
    public WebView webView;
    public Gson gson = new Gson();
    @Bind(R.id.img_game_maintenance)
    ImageView img_game_maintenance;
    private int version = Build.VERSION.SDK_INT;

    @Override
    public void initView() {
        super.initView();
        if (webView == null) {
            webView = (WebView) findViewById(setWebViewId());
        }
        webView.setWebViewClient(new DigWebViewClient());
        //设置Web视图
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                if (version >= 23) {
//                    构架一个builder来显示网页中的对话框
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.Theme_AppCompat_Light_Dialog);
                    builder.setMessage(message);
                    builder.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            //点击确定按钮之后，继续执行网页中的操作
                            result.confirm();
                        }
                    });
                    builder.setCancelable(false);
                    builder.create();
                    builder.show();
                    return true;
                } else {
                    return super.onJsAlert(view, url, message, result);
                }
            }

            //处理prompt弹出框
            @Override
            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                //
                if (version >= 23) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.Theme_AppCompat_Light_Dialog);
                    builder.setMessage(message);
                    builder.setNegativeButton(android.R.string.cancel, new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            result.cancel();
                        }
                    });
                    builder.setPositiveButton(android.R.string.ok, new AlertDialog.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            //点击确定按钮之后，继续执行网页中的操作
                            result.confirm();
                        }
                    });
                    builder.setCancelable(false);
                    builder.create();
                    builder.show();
                    return true;
                } else {
                    return super.onJsConfirm(view, url, message, result);
                }
            }

        });
        webViewSetting();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        toolbar.setBackgroundResource(0);
        initGameStatus();
    }

    public String getType2() {
        return "";
    }

    private void initGameStatus() {
        HttpUtils.httpPost(WebSiteUrl.GameMaintenanceUrl, "type2=" + getType2() + "&web_id=" + WebSiteUrl.WebId, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                GameMaintenanceBean gameMaintenanceBean = gson.fromJson(s, GameMaintenanceBean.class);
                String status = gameMaintenanceBean.getData().getStatus();
                if (status.equals("1")) {
                    webView.setVisibility(View.GONE);
                    img_game_maintenance.setVisibility(View.VISIBLE);
                } else {
                    initGameData();
                }
            }

            @Override
            public void onRequestFailed(String s) {

            }
        });
    }

    public void initGameData() {

    }

    @Override
    protected int getLayoutRes() {
        return setLayoutRes();
    }

    public int setLayoutRes() {
        return R.layout.activity_web;
    }

    public int setWebViewId() {
        return R.id.web_wv;
    }

    public void onLoadFinish() {
        dismissBlockDialog();
    }

    public void webViewSetting() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.getSettings().setAppCacheEnabled(true);//是否使用缓存
        webView.getSettings().setDomStorageEnabled(true);//DOM Storage
    }

    public void openUrl(String gameUrl) {
        if (gameUrl.startsWith("http")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(gameUrl);
            intent.setData(content_url);
            startActivity(intent);
        }
    }

    //Web视图
    private class DigWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            onLoadFinish();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();//接受证书
            dismissBlockDialog();
        }
    }
}
