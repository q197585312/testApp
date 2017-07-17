package com.example.skinpackage;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.skinpackage.zese.R;

/**
 * Created by Administrator on 2017/7/7.
 */

public class WelcomeActivity extends AppCompatActivity {


    TextView testTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        testTv = (TextView) findViewById(R.id.test_tv);
        Intent intent = getIntent();
        String action = intent.getAction();
        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = intent.getData();
            if (uri != null) {
                String host = uri.getHost();
                String dataString = intent.getDataString();
                String id = uri.getQueryParameter("id");
                String path = uri.getPath();

                String queryString = uri.getQuery();
                String sc=uri.getScheme();
                StringBuilder builder = new StringBuilder();

                builder.append("uri:" + dataString + "\n");
                builder.append("Scheme:" + sc + "\n");
                builder.append("host:" + host + "\n");
                builder.append("path:" + path + "\n");
                builder.append("queryString:" + queryString + "\n");
                builder.append("id:" + id + "\n");
                testTv.setText(builder.toString());
            }
        }
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
