package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/2/23.
 */
public class DigWebActivity extends BaseActivity {
    @BindView(R.id.web_wv)
    WebView webView;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }
    @Override
    protected void initData(Bundle savedInstanceState) {
        //设置窗口风格为进度条
        super.initData(savedInstanceState);
        loadweb();
    }
    private void loadweb(){
        //设置WebView属性，能够执行Javascript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportZoom(true);          //支持缩放
        webView.getSettings().setBuiltInZoomControls(true);  //启用内置缩放装置
        setDialog(new BlockDialog(DigWebActivity.this, getString(R.string.zhengjiazai)));
        showBlockDialog();
        //加载需要显示的网页
        webView.loadUrl(WebSiteUrl.ForgotPassword);
        //设置Web视图
        webView.setWebViewClient(new DigWebViewClient());
        webView.setWebChromeClient(new WebChromeClient() {
            //当WebView进度改变时更新窗口进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //Activity的进度范围在0到10000之间,所以这里要乘以100
                DigWebActivity.this.setProgress(newProgress * 100);
            }
        });
    }
    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            finish();
            return true;
        }
        return false;
    }
    //Web视图
    private class DigWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            dismissBlockDialog();
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            dismissBlockDialog();
        }
    }

}
