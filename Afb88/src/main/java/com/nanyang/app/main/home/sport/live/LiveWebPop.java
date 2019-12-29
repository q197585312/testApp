package com.nanyang.app.main.home.sport.live;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.additional.AddMBean;
import com.nanyang.app.main.home.sport.dialog.WebPop;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.widget.VideoPlayer;
import com.unkonw.testapp.libs.widget.listener.VideoListener;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by Administrator on 2019/11/28.
 */

public class LiveWebPop extends WebPop {


    private LinearLayout parentLl;
    private BallAdapterHelper adapterHelper;
    private TextView tv_title_live_stream;
    private TextView tv_title_live_center;
    private VideoPlayer videoPlayer;
    private boolean isPlay = true;
    private String livePlayUrl;

    public LiveWebPop(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    //  setContentView(R.layout.activity_live_web);
    @Override
    protected int onSetLayoutRes() {
        return R.layout.activity_live_web;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        videoPlayer = view.findViewById(R.id.video_player_stream);
        parentLl = view.findViewById(R.id.common_ball_parent_ll);
        tv_title_live_stream = view.findViewById(R.id.tv_title_live_stream);
        tv_title_live_center = view.findViewById(R.id.tv_title_live_center);
        tv_title_live_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibleLive(tv_title_live_center, webView, false);
            }
        });
        tv_title_live_stream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibleLive(tv_title_live_stream, videoPlayer, true);
            }
        });
        videoPlayer.setVideoListener(new VideoListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                Log.d("ijk","onBufferingUpdate");
            }

            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.d("ijk","onCompletion");
            }

            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                Log.d("ijk","onError");
                return false;

            }

            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                Log.d("ijk","onInfo");
                return false;
            }

            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                videoPlayer.start();
                Log.d("ijk","onPrepared");
            }

            @Override
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                Log.d("ijk","onSeekComplete");
            }

            @Override
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
                Log.d("ijk","onVideoSizeChanged");
            }
        });
        videoPlayer.setPath("rtmp://58.200.131.2:1935/livetv/hunantv");
        try {
            videoPlayer.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void visibleLive(TextView liveTv, View liveView, boolean isPlay) {
        this.isPlay = true;
        tv_title_live_center.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        tv_title_live_stream.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        webView.setVisibility(View.GONE);
        videoPlayer.setVisibility(View.GONE);
        liveView.setVisibility(View.VISIBLE);
        liveTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.line_yellow);
        /*if (isPlay) {
            videoPlayer.start();
        } else {
            videoPlayer.pause();
        }*/

    }

    public void setAdditionData(AddMBean additionData, BallAdapterHelper adapterHelper, BallInfo item) {
        this.adapterHelper = adapterHelper;
        adapterHelper.createAddedData(item, parentLl, additionData);
    }

    @Override
    protected void onClose() {
        super.onClose();
        adapterHelper.setIsLiveOpen(false);
    }

    public void setLivePlayUrl(String livePlayUrl)  {
        this.livePlayUrl = livePlayUrl;


    }
}
