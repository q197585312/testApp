package com.nanyang.app.main.home.sport.live;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.widget.VideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ViewHolder {
    @BindView(R.id.video_player_stream)
    public VideoPlayer videoPlayerStream;
    @BindView(R.id.web_wv)
    public WebView web_wv;
    @BindView(R.id.iv_play_status)
    public ImageView ivPlayStatus;
    @BindView(R.id.iv_voice)
    public ImageView ivVoice;
    @BindView(R.id.iv_full_screen)
    public ImageView ivFullScreen;
    @BindView(R.id.tv_run_time)
    public TextView tv_run_time;
    @BindView(R.id.ll_status)
    public LinearLayout llStatus;
    @BindView(R.id.fl_top_video)
    public LinearLayout fl_top_video;

    @BindView(R.id.ll_back_title_line)
    public View ll_back;

    @BindView(R.id.iv_left_back)
    public View iv_left_back;

    @BindView(R.id.tv_match_play)
    public View tv_match_play;

    @BindView(R.id.iv_title_live_stream)
    public ImageView tv_title_live_stream;

    @BindView(R.id.iv_title_live_center)
    public ImageView tv_title_live_center;

    @BindView(R.id.iv_bet_list)
    public View iv_bet_list;

    @BindView(R.id.fl_run_match_title)
    public View fl_run_match_title;
    @BindView(R.id.ll_run_match_title)
    public View ll_run_match_title;
    @BindView(R.id.tv_run_match_home)
    public TextView tv_run_match_home;
    @BindView(R.id.tv_run_match_home_score)
    public TextView tv_run_match_home_score;
    @BindView(R.id.tv_run_match_away)
    public TextView tv_run_match_away;
    @BindView(R.id.tv_run_match_away_score)
    public TextView tv_run_match_away_score;

    public ViewHolder(View view) {
        ButterKnife.bind(this, view);
    }

}