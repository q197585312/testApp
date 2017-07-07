package com.example.skinbluepackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by Administrator on 2017/7/7.
 */

public class WelcomeActivity extends AppCompatActivity {
    WebView testWeb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        testWeb= (WebView) findViewById(R.id.testWeb);
        loadHtml();
    }
    public void loadHtml()
    {

        WebSettings wSet = testWeb.getSettings();
        wSet.setJavaScriptEnabled(true);
        testWeb.loadUrl("http://www.appgd88.com/index.html");
    }
}
