package com.nanyang.app.main.home.sport.live;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.additional.AddMBean;
import com.unkonw.testapp.libs.widget.VideoPlayer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewHolder {
    @Bind(R.id.video_player_stream)
    public VideoPlayer videoPlayerStream;
    @Bind(R.id.web_wv)
    public WebView web_wv;
    @Bind(R.id.iv_play_status)
    public ImageView ivPlayStatus;
    @Bind(R.id.iv_voice)
    public ImageView ivVoice;
    @Bind(R.id.iv_full_screen)
    public ImageView ivFullScreen;
    @Bind(R.id.ll_status)
    public LinearLayout llStatus;
    @Bind(R.id.fl_top_video)
    public LinearLayout fl_top_video;
    @Bind(R.id.ll_bet_title)
    public View ll_bet_title;
    @Bind(R.id.ll_back_title_line)
    public View ll_back;

    @Bind(R.id.iv_left_back)
    public View iv_left_back;

    @Bind(R.id.tv_match_play)
    public View tv_match_play;

    @Bind(R.id.iv_title_live_stream)
    public ImageView tv_title_live_stream;

    @Bind(R.id.iv_title_live_center)
    public ImageView tv_title_live_center;

    @Bind(R.id.iv_bet_list)
    public View iv_bet_list;

    @Bind(R.id.tv_run_match_title)
    public TextView tv_run_match_title;


    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }

    public void updateRunTitle(AddMBean data) {
        tv_run_match_title.setText(data.getHomeName() + " "+data.go+" " + data.getHomeName());
    }
}