package nanyang.com.dig88.Home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import nanyang.com.dig88.Activity.BaseWebActivity;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2018/1/10.
 */

public class GameWebActivity extends BaseWebActivity {
    @Override
    public int getWebViewId() {
        return R.id.web_wv;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setleftViewEnable(true);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String title = intent.getStringExtra("title");
        if (url.startsWith("http://jdb")) {
            llParent.setBackgroundResource(R.mipmap.base_bg);
            toolbar.setVisibility(View.VISIBLE);
            setleftViewEnable(true);
        }
        setTitle(title);
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
