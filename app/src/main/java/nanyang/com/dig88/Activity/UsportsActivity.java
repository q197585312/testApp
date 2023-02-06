package nanyang.com.dig88.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import butterknife.Bind;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.SbosportsBean;
import nanyang.com.dig88.Entity.SsportLoginBean;
import nanyang.com.dig88.Entity.SsportRegiseBean;
import nanyang.com.dig88.Forex.UsportBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/10/12.
 */
public class UsportsActivity extends BaseActivity {
    @Bind(R.id.web_wv)
    WebView webView;
    HttpClient httpClient;
    private Thread GetSportTokenThread = null;
    private int version = Build.VERSION.SDK_INT;
    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == 0) {
                loadweb((String) msg.obj);
            } else if (msg.what == 1) {
                dismissBlockDialog();
                Toast.makeText(getApplication(), "login error", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //设置窗口风格为进度条
        super.initData(savedInstanceState);
        httpClient = new HttpClient("");
        AppTool.setAppLanguage(mContext, "");
        setDialog(new BlockDialog(mContext, getString(R.string.zhengjiazai)));
        showBlockDialog();
        GetSportToken getSportToken = new GetSportToken();
        GetSportTokenThread = new Thread(getSportToken);
        GetSportTokenThread.start();
    }

    /*webid + 用户名 + PHPSESSID  就是token*/
    private void loadweb(String url) {
        //设置WebView属性，能够执行Javascript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);// 设置允许访问文件数据
        webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
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
                    final AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.Theme_AppCompat_Light_Dialog);
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
        webView.setWebViewClient(new DigWebViewClient());
        webView.loadUrl(url);
    }

    private void isGoBack() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            ViewGroup view = (ViewGroup) getWindow().getDecorView();
            view.removeAllViews();
            super.leftClick();
        }

    }

    @Override
    protected void leftClick() {
        isGoBack();
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            isGoBack();
            return true;
        }
        return false;
    }

    public class GetSportToken implements Runnable {
        @Override
        public void run() {
            Gson gson = new Gson();
            try {
                String url = "http://ugsports.dig88api.com/login.php?token=" + getUserInfoBean().getSession_id() +
                        "&language=" + AppTool.getAppLanguage(mContext) + "&loginFrom=Smart";
                String result = httpClient.sendPost(url, "");
                UsportBean usportBean = gson.fromJson(result, UsportBean.class);
                if (TextUtils.isEmpty(usportBean.getData())) {
                    handler.sendEmptyMessage(1);
                } else {
                    handler.sendMessage(handler.obtainMessage(0, usportBean.getData()));
                }
            } catch (IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(1);
            }

        }
    }

    //Web视图
    private class DigWebViewClient extends WebViewClient {

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
            Log.d("Game", errorCode + "," + description + "," + failingUrl);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //handler.cancel(); 默认的处理方式，WebView变成空白页
            handler.proceed();//接受证书
            dismissBlockDialog();
            //handleMessage(Message msg); 其他处理
        }

    }
}
