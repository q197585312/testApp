package com.nanyang.app.main.home.sport.live;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.utils.LogUtil;
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
    private IRTMatchInfo itemBall;
    private int playType = 0;


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
        holder.tv_title_live_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResumeWeb();
            }
        });
        holder.tv_title_live_stream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResumePlay();
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


    protected void onResumePlay() {
        if (checkLivePlayVisible(itemBall)) {
            if (playType == 2) {
                holder.web_wv.onPause();
            }
            holder.ll_run_match_title.setVisibility(View.VISIBLE);
            holder.videoPlayerStream.setVisibility(View.VISIBLE);
            holder.web_wv.setVisibility(View.GONE);
            holder.llStatus.setVisibility(View.VISIBLE);
            holder.tv_run_time.setVisibility(View.VISIBLE);
            LogUtil.d("");
            playType = 1;
            try {
                if (holder.videoPlayerStream != null && holder.videoPlayerStream.getmPath() != null)
                    holder.videoPlayerStream.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            path = "rtmp://pull.prosportslive.net/live/" + BID;
            if (BID.startsWith("1")) {
                path = "rtmp://pull3.prosportslive.net/live/" + BID;
            } else if (BID.startsWith("8")) {
                path = "rtmp://pull2.prosportslive.net/live/" + BID;
            }

        }
        holder.videoPlayerStream.setPath(path);
    }

    public void openRunMatch(IRTMatchInfo itemBall) {
        this.itemBall = itemBall;
        holder.fl_top_video.setVisibility(View.VISIBLE);
        if (checkLivePlayVisible(itemBall)) {
            initPlayer(itemBall.getTvPathIBC());
            onResumePlay();
        } else {
            onResumeWeb();
        }
        holder.tv_run_match_home.setText(itemBall.getHome());
        holder.tv_run_match_away.setText(itemBall.getAway());
        holder.tv_run_match_home_score.setText(itemBall.getRunHomeScore());
        holder.tv_run_match_away_score.setText(itemBall.getRunAwayScore());
    }

    private void onResumeWeb() {
        if (checkWebRtsVisible(itemBall)) {
            holder.videoPlayerStream.setVisibility(View.GONE);
            holder.web_wv.setVisibility(View.VISIBLE);
            holder.llStatus.setVisibility(View.GONE);
            holder.tv_run_time.setVisibility(View.GONE);
            holder.ll_run_match_title.setVisibility(View.GONE);
            if (playing) {
                onPausePlay();
            }
            webLoad();
        }
    }

    private void webLoad() {
        playType = 2;
        String lag = AfbUtils.getLanguage(context);
        String l = "en";
        if (lag.equals("zh")) {
            l = "zh";
        }
        String gameUrl = AppConstant.getInstance().URL_RUNNING_MATCH_WEB + "?Id=" + itemBall.getRTSMatchId() + "&Home=" + StringUtils.URLEncode(itemBall.getHome()) + "&Away=" + StringUtils.URLEncode(itemBall.getAway()) + "&L=" + l;
        AfbUtils.synCookies(context, holder.web_wv, gameUrl,false);
        holder.web_wv.onResume();
    }

    public boolean checkWebRtsVisible(IRTMatchInfo itemBall) {
        return (itemBall != null && !StringUtils.isNull(itemBall.getRTSMatchId()) && !itemBall.getRTSMatchId().equals("0"));
    }

    public boolean checkLivePlayVisible(IRTMatchInfo itemBall) {
        return (itemBall != null && (!StringUtils.isNull(itemBall.getTvPathIBC()) && !itemBall.getTvPathIBC().equals("0")));
    }

}
