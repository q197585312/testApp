package com.nanyang.app.main.home.sport.live;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.widget.VideoPlayer;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewHolder {
        @Bind(R.id.video_player_stream)
        VideoPlayer videoPlayerStream;
        @Bind(R.id.iv_play_status)
        ImageView ivPlayStatus;
        @Bind(R.id.iv_voice)
        ImageView ivVoice;
        @Bind(R.id.iv_full_screen)
        ImageView ivFullScreen;
        @Bind(R.id.ll_status)
        LinearLayout llStatus;
        @Bind(R.id.fl_top_video)
        FrameLayout flTopContent;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }