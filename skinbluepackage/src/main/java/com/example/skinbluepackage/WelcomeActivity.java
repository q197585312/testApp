package com.example.skinbluepackage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.skinbluepackage.blue.R;

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
    /*@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = intent.getData();
            if (uri != null) {
                String host = uri.getHost();
                String dataString = intent.getDataString();
                String id = uri.getQueryParameter("id");
                String path = uri.getPath();
                String path1 = uri.getEncodedPath();
                String queryString = uri.getQuery();
                Log.d("Alex", "host:"+host);
                Log.d("Alex", "dataString:" + dataString);
                Log.d("Alex", "id:" + id);
                Log.d("Alex", "path:" + path);
                Log.d("Alex", "path1:" + path1);
                Log.d("Alex", "queryString:" + queryString);
            }
        }
    }

看下Log情况，如下：
[html] view plain copy
host:schemedemo
dataString:paraches://schemedemo/get/info?id=10000
id:10000
path:/get/info
path1:/get/info
queryString:id=10000  */
}
