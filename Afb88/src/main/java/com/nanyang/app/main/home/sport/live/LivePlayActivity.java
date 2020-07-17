package com.nanyang.app.main.home.sport.live;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.widget.VideoPlayer;
import com.unkonw.testapp.libs.widget.listener.VideoListener;

import java.io.IOException;

import butterknife.BindView;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by Administrator on 2020/1/3.
 */

public class LivePlayActivity extends BaseToolbarActivity {
    @BindView(R.id.video_player_stream)
    VideoPlayer videoPlayer;
    @BindView(R.id.iv_play_status)
    ImageView ivPlayStatus;
    @BindView(R.id.iv_voice)
    ImageView ivVoice;
    @BindView(R.id.iv_full_screen)
    ImageView ivFullScreen;
    @BindView(R.id.ll_status)
    LinearLayout llStatus;
    private String url;
    private boolean playing = true;
    private boolean voiceOpen = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_live_play);
    }

    @Override
    public void initData() {
        super.initData();
        tvToolbarTitle.setText(R.string.live_stream);
        toolbar.setNavigationIcon(R.mipmap.arrow_white_back);
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish();
            return;
        }
        ivFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackCLick(v);
            }
        });
        ivPlayStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnPause();
            }
        });
        ivVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnVoice();
            }
        });
        this.url = extras.getString(AppConstant.KEY_STRING);
        videoPlayer.setPath(url);
        videoPlayer.setVideoListener(new VideoListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                Log.d("ijk", "onBufferingUpdate");
            }

            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.d("ijk", "onCompletion");
            }

            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                Log.d("ijk", "onError");
                return false;

            }

            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                Log.d("ijk", "onInfo");
                return false;
            }

            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                onStartPlay();
                Log.d("ijk", "onPrepared");
            }

            @Override
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                Log.d("ijk", "onSeekComplete");
            }

            @Override
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
                Log.d("ijk", "onVideoSizeChanged");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        playVideo();
    }

    public void onStartPlay() {
        if (videoPlayer != null) {
            Log.d("ijk", "onPause");
            videoPlayer.start();
            playing = true;
            ivPlayStatus.setImageResource(R.mipmap.play_pause_white);
        }
    }

    public void onPausePlay() {
        if (videoPlayer != null) {
            Log.d("ijk", "onPause");
            videoPlayer.pause();
            playing = false;
            ivPlayStatus.setImageResource(R.mipmap.play_start_white);
        }
    }

    private void playVideo() {
        try {
            if (videoPlayer != null && videoPlayer.getmPath() != null)
                videoPlayer.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnVoice() {
        voiceOpen = !voiceOpen;
        if (voiceOpen) {
            videoPlayer.getmMediaPlayer().setVolume(1.0f, 1.0f);
            ivVoice.setImageResource(R.mipmap.play_mute_white);
        } else {
            videoPlayer.getmMediaPlayer().setVolume(0.0f, 0.0f);
            ivVoice.setImageResource(R.mipmap.play_voice_white);
        }
    }

    public void turnPause() {
        playing = !playing;
        if (playing) {
            onStart();
        } else {
            onPause();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPausePlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (videoPlayer != null) {
            Log.d("ijk", "stop");
            videoPlayer.stop();
            playing = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoPlayer != null) {
            Log.d("ijk", "onDestroy");
            videoPlayer.release();
        }
        BetGoalWindowUtils.clear();
    }
}
