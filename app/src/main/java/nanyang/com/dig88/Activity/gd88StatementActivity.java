package nanyang.com.dig88.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;

import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2018/1/10.
 */

public class gd88StatementActivity extends BaseWebActivity {
    @Override
    public int getWebViewId() {
        return R.id.web_wv;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        toolbar.setBackgroundResource(0);
        setleftViewEnable(true);
        setTitle("GD " + getString(R.string.wancenjilu));
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView.loadUrl(url);
    }

    @Override
    public void onStartedUrl(WebView view, String url, Bitmap favicon) {
        showBlockDialog();
    }

    @Override
    public void onFinishedLoad(WebView view, String url) {
        dismissBlockDialog();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }
}
