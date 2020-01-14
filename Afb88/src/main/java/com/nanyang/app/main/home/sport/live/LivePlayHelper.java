package com.nanyang.app.main.home.sport.live;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.unkonw.testapp.libs.widget.listener.VideoListener;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by Administrator on 2020/1/10.
 */

public class LivePlayHelper {
    ViewHolder holder;
    private boolean playing;
    private boolean voiceOpen;

    public LivePlayHelper(ViewHolder holder, Context context) {
        this.holder = holder;
        this.context = context;
        initClick();
    }

    private void initClick() {
        holder.ivVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnVoice();
            }
        });
        holder.ivPlayStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnPause();
            }
        });

    }

    public void turnPause() {
        playing = !playing;
        if (playing) {
            onStartPlay();
        } else {
            onPausePlay();
        }
    }
    public void onStartPlay() {
        if (holder.videoPlayerStream != null) {
            Log.d("ijk", "onPause");
            holder.videoPlayerStream.start();
            playing = true;
            holder.ivPlayStatus.setImageResource(R.mipmap.play_pause_white);
        }
    }


    public void turnVoice() {
        voiceOpen = !voiceOpen;
        if (voiceOpen) {
            holder.videoPlayerStream.getmMediaPlayer().setVolume(1.0f, 1.0f);
            holder.ivVoice.setImageResource(R.mipmap.play_mute_white);
        } else {
            holder.videoPlayerStream.getmMediaPlayer().setVolume(0.0f, 0.0f);
            holder.ivVoice.setImageResource(R.mipmap.play_voice_white);
        }
    }

    Context context;

    public void onDestroy() {
        if (holder.videoPlayerStream != null) {
            Log.d("ijk", "onDestroy");
            holder.videoPlayerStream.release();
        }
    }

    public void onStopPlay() {
        if (holder.videoPlayerStream != null) {
            Log.d("ijk", "stop");
            holder.videoPlayerStream.stop();
            playing = false;
        }
    }


    public void onPausePlay() {
        if (holder.videoPlayerStream != null) {
            Log.d("ijk", "onPause");
            holder.videoPlayerStream.pause();
            playing = false;
            holder.ivPlayStatus.setImageResource(R.mipmap.play_start_white);
        }
    }

    public void onResumePlay() {
        playVideo();
    }

    protected void playVideo() {
        try {
            if (holder.videoPlayerStream != null && holder.videoPlayerStream.getmPath() != null)
                holder.videoPlayerStream.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initPlayer(String livePlayUrlId) {
        holder.videoPlayerStream.setVideoListener(new VideoListener() {
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
        setLivePlayUrlId(livePlayUrlId);
    }

    public void setLivePlayUrlId(String BID) {

        Log.d("ijk", "BID:" + BID);
        String path;
        if (StringUtils.isNull(BID) || BID.equals("0")) {
            return;
        } else {
            path = "rtmp://pull.afb1188.com/live/" + BID;
            if (BID.startsWith("1")) {
                path = "rtmp://27.124.35.15:8080/live/" + BID;
            } else if (BID.startsWith("9")) {
                path = "rtmp://27.124.13.234:8080/live/" + BID;
            }

        }
        holder.videoPlayerStream.setPath(path);
    }
}
