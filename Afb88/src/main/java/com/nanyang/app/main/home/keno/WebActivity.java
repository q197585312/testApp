package com.nanyang.app.main.home.keno;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.AndroidBug5497Workaround;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;

/**
 * Created by Administrator on 2017/11/24.
 */

public class WebActivity extends BaseToolbarActivity {
    WebView webView;
    private boolean canFinish;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        AndroidBug5497Workaround.assistActivity(this);
        webView = (WebView) findViewById(R.id.web_wv);
        String url = getIntent().getStringExtra("url");

        String title = getIntent().getStringExtra(AppConstant.KEY_STRING);
        canFinish = getIntent().getBooleanExtra(AppConstant.KEY_BOOLEAN, false);
        if (title == null) {
            ToastUtils.showLong(R.string.failed_to_connect);
            finish();
            return;
        }
        //-https://www.onlinegames22.com/player/login/apiLogin0?userId=209122&agentId=a8&tokenId=gSxwaalV3647bSfRd7Sg%2F4qvfASgPPSdYntO%2B77guMA%3D&isMobileLogin=True&isShowSymbol=true&currencySymbol=&isApp=false&externalURL=https%3A%2F%2Fafb1188.com&gameCode=MX-LIVE-001&platform=SEXYBCRT&gameType=LIVE&isLaunchGame=true&language=cn
        //2020-09-29 10:49:44.922 22541-22541/com.nanyang.afb1188 D/ActivityThread:
        if (url.contains("www.onlinegames22.com")) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            // 设置为跟随系统sensor的状态
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        }
//        toolbar.setTitle(title);
//        tvToolbarTitle.setText(title);
        toolbar.setVisibility(View.GONE);
        showLoadingDialog();

        webView.getSettings().setJavaScriptEnabled(true);//是否允许JavaScript脚本运行，默认为false
        webView.getSettings().setDomStorageEnabled(true);//开启本地DOM存储
//        webView.loadUrl(url);
        //设置不用系统浏览器打开,直接显示在当前Webview
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                loadUrl(view, url);
                return true;
            }
        });

        loadWebView(url);
    }

//    public void saveImage(WebView webView) {
//        //图片打印
//        View view  = getWindow().getDecorView();
//        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
//        Canvas canvas = new Canvas(bitmap);
//        view.draw(canvas);
//        PrintHelper photoPrinter = new PrintHelper(this);
//        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
//        photoPrinter.printBitmap("droids.jpg - test print", bitmap);
//    }

    private void createWebPrintJob(WebView webView) {
        //create object of print manager in your device
        PrintManager printManager = (PrintManager) getSystemService(Context.PRINT_SERVICE);
        if (printManager != null) {
            //create object of print adapter
            PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

            //provide name to your newly generated pdf file
            String jobName = getString(R.string.app_name) + " Document";

            //open print dialog
            PrintAttributes printAttributes = new PrintAttributes.Builder().
                    setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();

            printManager.print(jobName, printAdapter, printAttributes);
        } else {
            // sorry, not supported
        }
    }

    public void loadUrl(WebView view, String url) {
        view.loadUrl(url);
    }

    private void loadWebView(String url) {
        LogUtil.d("url---", "-------" + url);
        webView.resumeTimers();
        AfbUtils.synCookies(this, webView, url, true, new WebViewClient() {

            @Override
            public void onLoadResource(WebView view, String url) {
                if (url.contains("IDAutomationStreamingLinear")) {
                    createWebPrintJob(webView);
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    url = request.getUrl().toString();
                    LogUtil.d("url", request.getUrl().toString());
                } else {
                    url = request.toString();
                    LogUtil.d("url", request.toString());
                }
                LogUtil.d("requesturl", url);
                ///_View/LiveDealerGDC.aspx?time=1596692876201
                if (url.contains("html?ispc=1"))
                    finish();
                else if (url.contains("/LiveDealerGDC.aspx")) {
                    goGd88App();
                } else {
                    loadUrl(view, url);
                }

                return true;
            }

        });
    }

    private void goGd88App() {
        getSkipGd88Data();
    }

    public void onResume() {
        super.onResume();
        webView.getSettings().setJavaScriptEnabled(true);

    }


    @Override
    protected void onStop() {
        super.onStop();
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        webView.getSettings().setJavaScriptEnabled(false);//是否允许JavaScript脚本运行，默认为false
    }


    @Override
    protected void onDestroy() {
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.destroy();
        }

        super.onDestroy();

    }


    @Override
    public void onBackCLick(View v) {
        if (canFinish && webView.canGoBack()) {
            webView.goBack();//返回上一页面
        } else {
            super.onBackCLick(v);
        }
    }
    /*  public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            }else{
                finish();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackCLick(View v) {

        if (webView.canGoBack()) {
            webView.goBack();//返回上一页面
        } else {
            finish();
            super.onBackCLick(v);
        }
    }*/
}
