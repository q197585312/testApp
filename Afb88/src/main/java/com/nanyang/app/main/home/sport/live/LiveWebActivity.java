package com.nanyang.app.main.home.sport.live;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/11/28.
 */

public class LiveWebActivity extends BaseToolbarActivity<LiveWebPresenter> {
    @Bind(R.id.iv_left_back)
    ImageView ivLeftBack;
    @Bind(R.id.tv_title_match)
    TextView tvTitleMatch;
    @Bind(R.id.iv_live_play)
    ImageView ivLivePlay;
    @Bind(R.id.web_wv)
    WebView webWv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_web);
        ButterKnife.bind(this);
        createPresenter(new LiveWebPresenter(this));
    }

    @Override
    public void initData() {
        super.initData();

    }
}
