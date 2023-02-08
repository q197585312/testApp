package nanyang.com.dig88.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.SharePreferenceUtil;
import nanyang.com.dig88.Entity.KlasPokerBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/10/12.
 */
public class klasPokerActivity extends BaseActivity {
    @BindView(R.id.web_wv)
    WebView webView;
    @BindView(R.id.web_left_back)
    ImageView exitImgl;
    HttpClient httpClient;
    String username;
    String ip;
    String klasPokerLoginUrl = "http://klaspoker.khmergaming.com/login.php";
    private Thread GetSportTokenThread = null;
    private int version = Build.VERSION.SDK_INT;
    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == 1) {
                loadweb((String) msg.obj);
            } else if (msg.what == 3) {
                dismissBlockDialog();
                Toast.makeText(mContext, "login error", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
        toolbar.setVisibility(View.GONE);
        exitImgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    private void goBack() {
        webView.destroy();
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        finish();
    }

    private String getLanguage() {
        String lg = AppTool.getAppLanguage(mContext);
        if (TextUtils.isEmpty(lg)) {
            return "eng";
        }
        switch (lg) {
            case "zh":
                return "chn";
            case "in":
                return "id";
            case "th":
                return "tai";
            case "vn":
                return "vie";
            case "en":
                return "eng";
        }
        return "eng";
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        httpClient = new HttpClient("");
        ip = SharePreferenceUtil.getString(mContext, "IP");
        AppTool.setAppLanguage(mContext, "");
        setDialog(new BlockDialog(mContext, getString(R.string.zhengjiazai)));
        showBlockDialog();
        GetSportToken getSportToken = new GetSportToken();
        GetSportTokenThread = new Thread(getSportToken);
        GetSportTokenThread.start();
        exitImgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });
    }

    private void loadweb(String url) {
        //设置WebView属性，能够执行Javascript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webView.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.getSettings().setAppCacheEnabled(true);//是否使用缓存
        webView.getSettings().setDomStorageEnabled(true);//DOM Storage
        //加载需要显示的网页
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
        webView.setWebViewClient(new DigWebViewClient());
        webView.loadUrl(url);
    }

    private void openUrl(String gameUrl) {
        if (gameUrl.startsWith("http")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(gameUrl);
            intent.setData(content_url);
            startActivity(intent);
        }
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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            isGoBack();
            return true;
        }
        return false;
    }

    private void setBackImgPlace() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 350;
        params.topMargin = 17;
        exitImgl.setLayoutParams(params);
    }

    public class GetSportToken implements Runnable {
        @Override
        public void run() {
            LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
            username = s.getUsername();
            String params = "web_id=" + WebSiteUrl.WebId + "&username=" + username +
                    "&ip=" + ip + "&language=" + getLanguage() + "&mobile=1";
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(klasPokerLoginUrl + "?" + params)
                    .build();
            Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();
                String result = response.body().string();
                Gson gson = new Gson();
                KlasPokerBean klasPokerBean = gson.fromJson(result, KlasPokerBean.class);
                handler.sendMessage(handler.obtainMessage(1, klasPokerBean.getUrl()));
            } catch (IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(3);
            }
        }
    }

    private class DigWebViewClient extends WebViewClient {

        @Override
        public void onPageFinished(WebView view, String url) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismissBlockDialog();
                }
            }, 5000);
            if (exitImgl != null) {
                exitImgl.setVisibility(View.VISIBLE);
                setBackImgPlace();
            }
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            dismissBlockDialog();
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
