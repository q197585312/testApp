package com.nanyang.app.main.home.sport.live;

import android.view.View;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiServiceKt;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.R;
import com.nanyang.app.Utils.LogIntervalUtils;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.load.login.LiveTvBean;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.widget.VideoHelper;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.RequestBody;

/**
 * Created by Administrator on 2020/1/10.
 */

public class LivePlayHelper {
    ViewHolder holder;
    private boolean playing;
    private boolean voiceOpen;
    private IRTMatchInfo itemBall;
    private int playType = 0;
    private boolean webloading;

    VideoHelper videoHelper;

    public LivePlayHelper(ViewHolder holder, SportActivity context) {
        this.holder = holder;
        videoHelper = new VideoHelper(context, holder.videoPlayerView);
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
                if (itemBall.getTvPathIBC().contains("GL")) {
                    String tvPathIBC = itemBall.getTvPathIBC();
                    LogUtil.d("tvPathIBC", "tvPathIBC:" + tvPathIBC);
                    if (tvPathIBC.contains("GL")) {
                        loadWebViewPlay(tvPathIBC);
                    }
                } else {
                    onResumePlay();
                }
            }
        });
    }


    public void turnPause() {
        playing = !playing;
        if (playing) {
            onResumePlay();
        } else {
            onStopPlay();
        }
    }


    public void turnVoice() {
        voiceOpen = !voiceOpen;
        if (voiceOpen) {
            videoHelper.getNodePlayer().setVolume(1.0f);
            holder.ivVoice.setImageResource(R.mipmap.play_mute_white);
        } else {
            videoHelper.getNodePlayer().setVolume(0.0f);
            holder.ivVoice.setImageResource(R.mipmap.play_voice_white);
        }
    }

    SportActivity context;

    public void onDestroy() {
        if (videoHelper.getNodePlayer() != null) {
            videoHelper.getNodePlayer().release();
        }
    }

    public void onStopPlay() {
        if (videoHelper.getNodePlayer() != null) {
            videoHelper.stopVideo();
            playing = false;
        }
    }


    public void onPausePlay() {
        if (videoHelper.getNodePlayer() != null) {
            videoHelper.getNodePlayer().pause();
            playing = false;
            holder.ivPlayStatus.setImageResource(R.mipmap.play_start_white);
        }
    }


    public void onResumePlay() {
        if (checkLivePlayVisible(itemBall)) {
            if (playType == 2) {
                holder.web_wv.loadUrl("");
                holder.web_wv.onPause();
            }
            holder.ll_run_match_title.setVisibility(View.VISIBLE);
            holder.videoPlayerView.setVisibility(View.VISIBLE);
            holder.web_wv.loadUrl("");
            holder.web_wv.setVisibility(View.GONE);
            webloading = false;
            holder.llStatus.setVisibility(View.VISIBLE);
            holder.tv_run_time.setVisibility(View.VISIBLE);
            playType = 1;
            LogIntervalUtils.logTime("Bid:" + itemBall.getTvPathIBC());
            try {
                if (videoHelper.getNodePlayer() != null) {
                    videoHelper.playVideo();
                    LogIntervalUtils.logTime("start:" + itemBall.getTvPathIBC());
                    playing = true;
                    holder.ivPlayStatus.setImageResource(R.mipmap.play_pause_white);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }


    public void setLivePlayUrlId(String TvPathIBC) {


        String path;
        if (StringUtils.isNull(TvPathIBC) || TvPathIBC.equals("0")) {
            return;
        } else {
            path = "rtmp://pull.prosportslive.net/live/" + TvPathIBC;
            if (TvPathIBC.startsWith("1")) {
                path = "rtmp://pull3.prosportslive.net/live/" + TvPathIBC;
            } else if (TvPathIBC.startsWith("8")) {
                path = "rtmp://pull2.prosportslive.net/live/" + TvPathIBC;
            }

        }

//        path = "rtmp://pull.prosportslive.net/live/331";
        videoHelper.setPlayUrl(path);
    }

    public void openRunMatch(IRTMatchInfo itemBall, BaseRetrofitPresenter presenter) {
        LogUtil.getMethodName("IRTMatchInfo:" + itemBall.getHome() + ",dbid:" + itemBall.getSocOddsId());
        if (this.itemBall == null || !this.itemBall.getSocOddsId().equals(itemBall.getSocOddsId())) {
            this.itemBall = itemBall;
            webloading = false;
        }
        holder.fl_top_video.setVisibility(View.VISIBLE);
        if (checkLivePlayVisible(itemBall) && !webloading && itemBall.getTvPathIBC() != null && !itemBall.getTvPathIBC().contains("GL")) {

            setLivePlayUrlId(itemBall.getTvPathIBC());
            onResumePlay();


        } else {
            String tvPathIBC = itemBall.getTvPathIBC();
            LogUtil.d("tvPathIBC", "tvPathIBC:" + tvPathIBC);
            if (tvPathIBC.contains("GL")) {

                loadWebViewPlay(tvPathIBC);

            } else {
                onResumeWeb();
            }
        }
        holder.tv_run_match_home.setText(itemBall.getHome());
        holder.tv_run_match_away.setText(itemBall.getAway());
        holder.tv_run_match_home_score.setText(itemBall.getRunHomeScore());
        holder.tv_run_match_away_score.setText(itemBall.getRunAwayScore());
    }

    public void loadWebViewPlay(String tvPathIBC) {
        String gliveid = tvPathIBC.replace("GL", "");
        LiveTvBean bean = new LiveTvBean("GetGliveUrl", gliveid);

        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), bean.toJson());

        context.presenter.doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().doPostJson(BuildConfig.HOST_AFB + "api/pgGetTVID", body), new BaseConsumer<String>(context) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                LogUtil.d("tvPathIBC", "data:" + data);
                JSONObject json = new JSONObject(data);
                String url = json.optString("data");
                onResumeWebPlay(url);
            }
        });
    }

    private void onResumeWeb() {
        if (checkWebRtsVisible(itemBall)) {
            holder.videoPlayerView.setVisibility(View.GONE);
            holder.web_wv.setVisibility(View.VISIBLE);
            holder.llStatus.setVisibility(View.GONE);
            holder.tv_run_time.setVisibility(View.GONE);
            holder.ll_run_match_title.setVisibility(View.GONE);
            if (playing) {
                onStopPlay();
            }
            webloading = true;
            webLoad();
        }
    }

    private void onResumeWebPlay(String url) {

        holder.videoPlayerView.setVisibility(View.GONE);
        holder.web_wv.setVisibility(View.VISIBLE);
        holder.llStatus.setVisibility(View.GONE);
        holder.tv_run_time.setVisibility(View.GONE);
        holder.ll_run_match_title.setVisibility(View.VISIBLE);
        if (playing) {
            onStopPlay();
        }
        webloading = true;
        webLoad(url);

    }

    private boolean checkWebLiveVisible(IRTMatchInfo itemBall) {
        return false;
    }

    private void webLoad() {
        playType = 2;
        String lag = AfbUtils.getLanguage(context);
        String l = "en";
        if (lag.equals("zh")) {
            l = "zh";
        }
        String gameUrl = AppConstant.getInstance().URL_RUNNING_MATCH_WEB + "?Id=" + itemBall.getRTSMatchId() + "&Home=" + StringUtils.URLEncode(itemBall.getHome()) + "&Away=" + StringUtils.URLEncode(itemBall.getAway()) + "&L=" + l;
        AfbUtils.synCookies(context, holder.web_wv, gameUrl, false);
        holder.web_wv.onResume();
    }

    private void webLoad(String url) {
        playType = 2;

        String gameUrl = url;
        LogUtil.d("tvPathIBC", "url:" + url);
        AfbUtils.synCookies(context, holder.web_wv, gameUrl, false);
        holder.web_wv.onResume();
    }

    public boolean checkWebRtsVisible(IRTMatchInfo itemBall) {
        return (itemBall != null && !StringUtils.isNull(itemBall.getRTSMatchId()) && !itemBall.getRTSMatchId().equals("0"));
    }

    public boolean checkLivePlayVisible(IRTMatchInfo itemBall) {
        return (itemBall != null && (!StringUtils.isNull(itemBall.getTvPathIBC()) && !itemBall.getTvPathIBC().equals("0")));
    }

}
