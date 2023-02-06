package nanyang.com.dig88.Activity;

import android.content.res.Configuration;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import java.io.IOException;

import butterknife.Bind;
import nanyang.com.dig88.Entity.AgFishConfigBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2017/9/4.
 */

public class AgFishActivity extends BaseActivity {
    @Bind(R.id.web_wv)
    WebView webView;
    @Bind(R.id.ll_parent)
    LinearLayout llParent;
    HttpClient httpClient;
    private Handler agHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String param = (String) msg.obj;
                    dismissBlockDialog();
                    loadweb(param);
                    break;
            }
        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
//        llParent.setBackgroundResource(R.mipmap.base_bg);
//        toolbar.setVisibility(View.VISIBLE);
//        toolbar.setBackgroundResource(0);
//        setleftViewEnable(true);
        setTitle("AG " + getString(R.string.Fishing));
        httpClient = new HttpClient("");
        getLoadUrl();
    }

    private void getLoadUrl() {
        showBlockDialog();
        new Thread() {
            @Override
            public void run() {
                String configUrl = "http://agcasino.dig88api.com/fish/index.php?web_id=" + WebSiteUrl.WebId + "&member_id=" +
                        getUserInfoBean().getUser_id() + "&language=" + Dig88Utils.getLanguage(mContext) + "&token=" +
                        getUserInfoBean().getSession_id() + "&domain=855kg.com";
                try {
                    String configResult = httpClient.sendPost(configUrl, "");
                    AgFishConfigBean agFishConfigBean = gson.fromJson(configResult, AgFishConfigBean.class);
                    String loginData = agFishConfigBean.getLogin_data();
                    String md5Login = agFishConfigBean.getMd5_login();
                    String param = "params=" + loginData + "&key=" + md5Login;
                    agHandler.sendMessage(handler.obtainMessage(1, param));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {
            webView.destroy();
        }
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        Dig88Utils.setLang(mContext);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            toolbar.setVisibility(View.GONE);
        } else {
            toolbar.setVisibility(View.VISIBLE);
        }
    }

    private void loadweb(String param) {
        //设置WebView属性，能够执行Javascript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.getSettings().setDomStorageEnabled(true);//DOM Storage
        webView.getSettings().setAllowFileAccess(true);
        //设置Web视图
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new DigWebViewClient());
        String loginUrl = "http://gci.m34.khmergaming.com:81/forwardGame.do";
        webView.postUrl(loginUrl, param.getBytes());
    }

    //Web视图
    private class DigWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            dismissBlockDialog();
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            dismissBlockDialog();
            Log.d("Game", errorCode + "," + description + "," + failingUrl);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            super.onReceivedSslError(view, handler, error);
            handler.proceed();//接受证书
            dismissBlockDialog();
        }

    }
}
